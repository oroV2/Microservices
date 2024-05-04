package com.eazybytes.cards.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("CARDS_MS");
	}



}
