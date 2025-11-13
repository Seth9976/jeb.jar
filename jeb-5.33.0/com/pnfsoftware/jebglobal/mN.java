package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;

public class mN {
   private final boolean pC;
   private NC A;
   private boolean kS;
   private List wS = new ArrayList();
   private KH UT = null;
   private Yg E = null;
   private int sY = 32;
   private boolean ys = false;
   private hg.Av ld = null;
   private mN.Av gp = mN.Av.pC;
   private mN.Sv oT = null;
   private Yg fI = null;
   private String WR = null;
   private String NS = null;
   private String vP = null;

   private mN(boolean var1, NC var2) {
      this.pC = var1;
      this.A = var2;
   }

   static mN pC(NC var0) {
      return new mN(true, var0);
   }

   static mN A(NC var0) {
      return new mN(false, var0);
   }

   void pC(Yg var1) {
      this.wS.add(var1);
   }

   void A(Yg var1) {
      if (var1 instanceof KH) {
         this.UT = (KH)var1;
      } else {
         throw new IllegalStateException(Strings.f("Not a valid Load Store operand:  %s (%s)", var1, var1.getClass()));
      }
   }

   void kS(NC var1) {
      if ((var1.pC().endsWith("B") || var1.pC().endsWith("BT"))
         && !var1.pC().equals("LDRAB")
         && !var1.pC().equals("LD64B")
         && !var1.pC().endsWith("MDB")
         && !var1.pC().endsWith("MIB")) {
         this.sY = 8;
      } else if ((var1.pC().endsWith("H") || var1.pC().endsWith("HT")) && !var1.pC().endsWith("PUSH")) {
         this.sY = 16;
      } else if (var1.pC().endsWith("W")) {
         this.sY = 32;
      } else if (Strings.isContainedIn(var1.pC(), "VLDR", "VSTR")) {
         if ("16".equals(var1.UT())) {
            this.sY = 16;
         } else {
            this.sY = -1;
         }
      } else {
         this.sY = -1;
      }
   }

   public void pC(int var1) {
      this.sY = var1;
   }

   void pC(String var1) {
      if (var1.endsWith("IB")) {
         this.ld = hg.Av.wS;
      } else if (Strings.endsWith(var1, "DB", "PUSH")) {
         this.ld = hg.Av.kS;
      } else if (var1.endsWith("DA")) {
         this.ld = hg.Av.A;
      } else {
         this.ld = hg.Av.pC;
      }
   }

   void pC(boolean var1) {
      this.kS = var1;
   }

   public boolean pC() {
      return this.kS;
   }

   public int A() {
      if (this.wS.isEmpty()) {
         throw new IllegalStateException("Illegal or corrupted Load/Store data");
      } else {
         Yg var1 = (Yg)this.wS.get(0);
         if (var1 instanceof rw) {
            var1 = ((rw)this.wS.get(0)).A();
            return var1 instanceof lw ? u.pC((lw)var1) : Gq.pC(this.A);
         } else {
            return var1 instanceof lw ? u.pC((lw)var1) : ((Yg)this.wS.get(0)).getOperandBitsize();
         }
      }
   }

   public Yg A(int var1) {
      return (Yg)this.wS.get(var1);
   }

   public int kS() {
      return this.wS.size();
   }

   public KH wS() {
      return this.UT;
   }

   public int UT() {
      return this.sY == -1 ? this.A() : this.sY;
   }

   void A(boolean var1) {
      this.ys = var1;
   }

   public boolean E() {
      return this.ys;
   }

   void kS(Yg var1) {
      this.E = var1;
   }

   public Yg sY() {
      return this.E;
   }

   public boolean ys() {
      return this.pC;
   }

   public Yg[] ld() {
      return (Yg[])this.wS.toArray(new Yg[this.wS.size()]);
   }

   public hg.Av gp() {
      return this.ld;
   }

   public mN.Av oT() {
      return this.gp;
   }

   void pC(mN.Av var1) {
      this.gp = var1;
   }

   void pC(String var1, Yg var2) {
      this.oT = this.UT(var1);
      this.fI = var2;
   }

   private mN.Sv UT(String var1) {
      String var2 = var1.substring(2, 5);
      if (var2.endsWith("MA")) {
         var2 = var2 + "X";
      } else if (var2.endsWith("MI")) {
         var2 = var2 + "N";
      }

      return mN.Sv.valueOf(var2);
   }

   public mN.Sv fI() {
      return this.oT;
   }

   public Yg WR() {
      return this.fI;
   }

   void A(String var1) {
      this.WR = Character.toString(var1.charAt(var1.length() - 1));
   }

   public String NS() {
      return this.WR;
   }

   void kS(String var1) {
      this.NS = var1;
   }

   public String vP() {
      return this.NS;
   }

   void wS(String var1) {
      this.vP = var1;
   }

   public String xC() {
      return this.vP;
   }

   public boolean ED() {
      return this.vP() != null || this.xC() != null;
   }

   public boolean Sc() {
      return this.NS() == null && this.vP() == null;
   }

   boolean ah() {
      return !this.wS.isEmpty() && this.UT != null;
   }

   public static enum Av {
      pC,
      A,
      kS,
      wS,
      UT,
      E;
   }

   public static enum Sv {
      pC,
      A,
      kS,
      wS,
      UT,
      E,
      sY,
      ys;
   }
}
