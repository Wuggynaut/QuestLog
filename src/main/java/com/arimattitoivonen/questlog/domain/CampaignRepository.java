package com.arimattitoivonen.questlog.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CampaignRepository extends CrudRepository<Campaign, Long> {
    List<Campaign> findByUser(AppUser user);
}
