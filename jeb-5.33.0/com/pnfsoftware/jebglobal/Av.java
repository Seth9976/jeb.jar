package com.pnfsoftware.jebglobal;

import java.util.Collection;
import java.util.HashMap;

public class Av {
   private long pC;
   private long A;
   private long kS;
   private long wS;
   private HashMap UT;
   private String E;
   private String sY;
   private String ys;

   public Av(long var1) {
      this.pC = var1;
      this.A = System.currentTimeMillis();
      this.kS = 0L;
      this.wS = 0L;
      this.UT = new HashMap();
   }

   public synchronized Collection pC() {
      return this.UT.values();
   }

   public synchronized int A() {
      return this.UT.size();
   }

   public synchronized void pC(long var1, String var3, int var4) {
      if (!this.UT.containsKey(var1)) {
         this.UT.put(var1, new com.pnfsoftware.jebglobal.Av.Av(var1, var3, var4));
      }
   }

   public synchronized boolean pC(long var1) {
      return this.UT.remove(var1) != null;
   }

   public long kS() {
      return this.A;
   }

   public synchronized long wS() {
      return this.kS;
   }

   public synchronized void UT() {
      this.kS = System.currentTimeMillis();
      this.wS++;
   }

   public synchronized void A(long var1, String var3, int var4) {
      this.pC(var1, var3, var4);
      this.UT();
   }

   public void pC(String var1) {
      this.E = var1;
   }

   synchronized String E() {
      return this.E;
   }

   public void A(String var1) {
      this.sY = var1;
   }

   synchronized String sY() {
      return this.sY;
   }

   public synchronized void kS(String var1) {
      this.ys = var1;
   }

   synchronized String ys() {
      return this.ys;
   }

   public static class Av {
      private long pC;
      private String A;
      private int kS;

      public Av(long var1, String var3, int var4) {
         this.pC = var1;
         this.A = var3;
         this.kS = var4;
      }

      public String pC() {
         return this.A;
      }

      public int A() {
         return this.kS;
      }
   }
}
