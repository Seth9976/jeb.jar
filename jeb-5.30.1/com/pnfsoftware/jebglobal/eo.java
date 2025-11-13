package com.pnfsoftware.jebglobal;

import java.util.Collection;
import java.util.HashMap;

public class eo {
   private long q;
   private long RF;
   private long xK;
   private long Dw;
   private HashMap Uv;

   public eo(long var1) {
      this.q = var1;
      this.RF = System.currentTimeMillis();
      this.xK = 0L;
      this.Dw = 0L;
      this.Uv = new HashMap();
   }

   public synchronized long q() {
      return this.q;
   }

   public synchronized Collection RF() {
      return this.Uv.values();
   }

   public synchronized int xK() {
      return this.Uv.size();
   }

   public synchronized void q(long var1, String var3, int var4) {
      if (!this.Uv.containsKey(var1)) {
         this.Uv.put(var1, new com.pnfsoftware.jebglobal.eo.eo(var1, var3, var4));
      }
   }

   public synchronized boolean q(long var1) {
      return this.Uv.remove(var1) != null;
   }

   public long Dw() {
      return this.RF;
   }

   public synchronized long Uv() {
      return this.xK;
   }

   public synchronized long oW() {
      return this.Dw;
   }

   public synchronized void gO() {
      this.xK = System.currentTimeMillis();
      this.Dw++;
   }

   public synchronized void RF(long var1, String var3, int var4) {
      this.q(var1, var3, var4);
      this.gO();
   }

   public static class eo {
      private long q;
      private String RF;
      private int xK;

      public eo(long var1, String var3, int var4) {
         this.q = var1;
         this.RF = var3;
         this.xK = var4;
      }

      public long q() {
         return this.q;
      }

      public String RF() {
         return this.RF;
      }

      public int xK() {
         return this.xK;
      }
   }
}
