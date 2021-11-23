package org.example.yugong.facadeSupplementary;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author qiaobao
 * @since --
 */
@Slf4j
public class FacadeSupplementaryTest {

    private static final HashMap<String, AgreementDTO> agreementMap = Maps.newHashMap();


    private static final HashMap<String, List<DepositOrderManageDTO>> depositOrderManage = Maps.newHashMap();

    static {

        AgreementDTO build2 = AgreementDTO.builder()
                .agreementCode("TGC202008160376100")
                .historyAgreementCode("TGC201907280245970")
                .leaseNum(2)
                .build();
        agreementMap.put(build2.getAgreementCode(), build2);
        AgreementDTO build3 = AgreementDTO.builder()
                .agreementCode("TGC201907280245970")
                .historyAgreementCode("TGC201808070050499")
                .leaseNum(1)
                .build();
        agreementMap.put(build3.getAgreementCode(), build3);

        AgreementDTO build4 = AgreementDTO.builder()
                .agreementCode("TGC201808070050499")
                .historyAgreementCode("")
                .leaseNum(0)
                .build();
        agreementMap.put(build4.getAgreementCode(), build4);

        DepositOrderManageDTO build = DepositOrderManageDTO.builder().paymentFee(1030000L).build();
        List<DepositOrderManageDTO> depositOrderManageDTOS = new ArrayList<>();
        depositOrderManageDTOS.add(build);
        depositOrderManage.put("TGC201907280245970", depositOrderManageDTOS);


    }

    public Long getExpireDepositFee(String agreementCode, Integer depositType) {
        AgreementDTO agreementDTO = agreementMap.get(agreementCode);
        Long totalAmount = 0L;
        log.info("getExpireDepositFee method agreementCode = {},depositType = {},LeaseNum={}", agreementCode, depositType, agreementDTO.getLeaseNum());
        if (agreementDTO.getLeaseNum() > 0) {
            totalAmount = totalAmount + getLeaseAgreementDepositAmount(agreementDTO, depositType, totalAmount, agreementDTO.getLeaseNum());
        }
        totalAmount = totalAmount + getTargetDepositTotalAmount(depositType, agreementCode);
        log.info("getExpireDepositFee totalAmount = {}", totalAmount);
        return totalAmount;
    }

    private Long getLeaseAgreementDepositAmount(AgreementDTO agreementDTO, Integer depositType, Long totalAmount, Integer loopNum) {
        log.info("getLeaseAgreementDepositAmount agreementCode = {},depositType ={},totalAmount={},loopNum = {}",
                agreementDTO.getAgreementCode(), depositType, totalAmount, loopNum);
        if (loopNum > 0) {
            String historyAgreementCode = agreementDTO.getHistoryAgreementCode();
            AgreementDTO historyAgreement = agreementMap.get(historyAgreementCode);

            //获取上一年合约的扣款单
            totalAmount = totalAmount + getTargetDepositTotalAmount(depositType, historyAgreementCode);
            getLeaseAgreementDepositAmount(historyAgreement, depositType, totalAmount, loopNum - 1);
        }
        return totalAmount;
    }


    private Long getTargetDepositTotalAmount(Integer depositType, String agreementCode) {
        Long totalFee = 0L;
        log.info("getTargetDepositTotalAmount depositType = {},agreementCode={}", depositType, agreementCode);


        List<DepositOrderManageDTO> depositOrderManageDTOList = depositOrderManage.get(agreementCode);
        log.info("getTargetDepositTotalAmount  agreementCode ={}", agreementCode);
        if (!CollectionUtils.isEmpty(depositOrderManageDTOList)) {
            for (DepositOrderManageDTO depositOrderManageDTO : depositOrderManageDTOList) {

                log.info("depositType = {},agreementCode = {}", depositType, agreementCode);
                totalFee += depositOrderManageDTO.getPaymentFee();
                log.info("totalFee = {}", totalFee);

            }
        }
        return totalFee;
    }

    public static void main(String[] args) {
        FacadeSupplementaryTest facadeSupplementaryTest = new FacadeSupplementaryTest();
        Long depositFee = facadeSupplementaryTest.getExpireDepositFee("TGC202008160376100", 2);
        System.out.println(depositFee);

    }


}
