package org.example.accounter.basic_info.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.accounter.basic_info.dto.SupplierUpdateRequest;
import org.example.accounter.core.constants.MainType;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("거래처 이름")
    @Column(name = "name")
    private String name;

    @Comment("거래처 대표자명")
    @Column(name = "repr_name")
    private String representationName;

    @Comment("거래처 사업자 번호")
    @Column(name = "corp_code")
    private String corpCode;

    @Comment("거래처 주소")
    @Column(name = "address")
    private String address;

    @Comment("업태")
    @Column(name = "category")
    private String category;

    @Comment("주 거래처 여부")
    @ColumnDefault("false")
    @Enumerated(EnumType.STRING)
    @Column(name = "is_main_supplier")
    private MainType main;

    public void update(SupplierUpdateRequest request) {
        this.name = request.getName();
        this.representationName = request.getRepresentationName();
        this.corpCode = request.getCorpCode();
        this.address = request.getAddress();
        this.category = request.getCategory();
        this.main = request.getMain();
    }
}
