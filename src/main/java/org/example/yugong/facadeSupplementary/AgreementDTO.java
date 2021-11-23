package org.example.yugong.facadeSupplementary;

import lombok.Builder;
import lombok.Data;

/**
 * @author qiaobao
 * @since --
 */
@Data
@Builder
public class AgreementDTO {
    private Integer leaseNum;

    private String agreementCode;

    private String historyAgreementCode;
}
