package org.example.accounter.slip.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import static org.example.accounter.slip.entity.QSlip.slip;

import org.example.accounter.slip.controller.request.SlipFilterRequest;
import org.example.accounter.slip.entity.QSlip;
import org.example.accounter.slip.repository.rdto.RSlipListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SlipCustomRepositoryImpl implements SlipCustomRepository {

    private final JPAQueryFactory query;

    @Override
    public Page<RSlipListDto> findAllDto(Pageable pageable, SlipFilterRequest request) {

        List<RSlipListDto> list = query.select(
                Projections.bean(
                        RSlipListDto.class,
                        slip.id,
                        slip.type,
                        slip.desc,
                        slip.transactionDateTime.as("transDttm"),
                        slip.registrationDate.as("regDttm")
                        )
        )
                .from(slip)
                .where(this.getFilterWhere(slip, request))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(slip.id.asc())
                .fetch();

        Long count = query
                .select(slip.count())
                .from(slip)
                .where(getFilterWhere(slip, request))
                .fetchOne();

        return new PageImpl<>(list, pageable, count);
    }

    private BooleanBuilder getFilterWhere(QSlip sip, SlipFilterRequest request) {
        BooleanBuilder builder = new BooleanBuilder();

        if(request.getStartRegDttm() != null && request.getEndRegDttm() != null) {
            builder
                    .and(slip
                            .registrationDate
                            .goe(request.getStartRegDttm())
                    )
                    .and(slip
                            .registrationDate
                            .loe(request.getEndRegDttm())
                    );
        }

        if(request.getStartTranDttm() != null && request.getEndTranDttm() != null) {
            builder
                    .and(slip
                            .transactionDateTime
                            .goe(request.getStartTranDttm())
                    )
                    .and(slip
                            .transactionDateTime
                            .loe(request.getEndTranDttm())
                    );
        }

        if(request.getSlip() != null) {
            builder.and(slip.type.eq(request.getSlip()));
        }

        if(request.getKeyword() != null) {
            builder.and(slip.desc.contains(request.getKeyword()));
        }

        return builder;
    }

}
