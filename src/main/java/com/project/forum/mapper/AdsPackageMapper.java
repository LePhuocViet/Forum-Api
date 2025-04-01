package com.project.forum.mapper;

import com.project.forum.dto.requests.ads.AdsPackageRequest;
import com.project.forum.dto.responses.ads.AdsPackageResponse;
import com.project.forum.enity.AdsPackage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdsPackageMapper {

    AdsPackageResponse toAdsPackageResponse(AdsPackage adsPackage);

    AdsPackage toUpdateAdsPackage(AdsPackage adsPackage, AdsPackageRequest adsPackageRequest);

}
