package com.danielsolawa.storeauth.mappers;

import com.danielsolawa.storeauth.domain.ActivationToken;
import com.danielsolawa.storeauth.dtos.ActivationTokenDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ActivationTokenMapper {

    ActivationTokenMapper INSTANCE = Mappers.getMapper(ActivationTokenMapper.class);

    ActivationToken activationTokenDtoToActivationToken(ActivationTokenDto activationTokenDto);
    ActivationTokenDto activationTokenToActivationTokenDto(ActivationToken activationToken);
}
