package com.danielsolawa.storeauth.mappers;

import com.danielsolawa.storeauth.domain.ActivationToken;
import com.danielsolawa.storeauth.dtos.ActivationTokenDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ActivationTokenMapperTest {

    ActivationTokenMapper activationTokenMapper;

    @Before
    public void setUp() throws Exception {
        activationTokenMapper = ActivationTokenMapper.INSTANCE;
    }

    @Test
    public void activationTokenDtoToActivationTokenHappyPath() {
        ActivationTokenDto activationTokenDto = new ActivationTokenDto();
        activationTokenDto.setId(1L);

        ActivationToken activationToken =
                activationTokenMapper.activationTokenDtoToActivationToken(activationTokenDto);

        assertEquals(activationTokenDto.getId(), activationToken.getId());
    }

    @Test
    public void activationTokenDtoToActivationTokenEmpty() {

        ActivationToken activationToken =
                activationTokenMapper.activationTokenDtoToActivationToken(null);

        assertNull(activationToken);
    }



    @Test
    public void activationTokenToActivationTokenDtoHappyPath() {
        ActivationToken activationToken = new ActivationToken();
        activationToken.setId(1L);

        ActivationTokenDto activationTokenDto =
                activationTokenMapper.activationTokenToActivationTokenDto(activationToken);

        assertEquals(activationToken.getId(), activationTokenDto.getId());

    }

    @Test
    public void activationTokenToActivationTokenDtoEmpty() {

        ActivationTokenDto activationTokenDto =
                activationTokenMapper.activationTokenToActivationTokenDto(null);

        assertNull(activationTokenDto);
    }
}