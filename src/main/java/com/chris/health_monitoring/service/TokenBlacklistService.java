// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.chris.health_monitoring.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistService {
   private final Set<String> blacklistedTokens = new HashSet();

   public TokenBlacklistService() {
   }

   public void blacklist(String token) {
      this.blacklistedTokens.add(token);
   }

   public boolean isBlacklisted(String token) {
      return this.blacklistedTokens.contains(token);
   }
}
