package com.chutneytesting.campaign.infra.jpa;

import static java.util.stream.Collectors.toSet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Set;

@Entity(name = "CAMPAIGN_PARAMETERS")
public class CampaignParameterEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAMPAIGN_ID")
    private CampaignEntity campaign;

    @Column(name = "PARAMETER")
    private String parameter;

    @Column(name = "PARAMETER_VALUE")
    private String value;

    CampaignParameterEntity() {
    }

    public CampaignParameterEntity(String parameter, String value) {
        this(null, parameter, value);
    }

    public CampaignParameterEntity(CampaignEntity campaign, String parameter, String value) {
        this.campaign = campaign;
        this.parameter = parameter;
        this.value = value;
    }

    public String parameter() {
        return parameter;
    }

    public String value() {
        return value;
    }

    public void forCampaign(CampaignEntity campaign) {
        this.campaign = campaign;
    }

    public static Set<CampaignParameterEntity> fromDomain(com.chutneytesting.server.core.domain.scenario.campaign.Campaign campaign) {
        return campaign.executionParameters.entrySet().stream()
            .map(e -> new CampaignParameterEntity(e.getKey(), e.getValue()))
            .collect(toSet());
    }
}