package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;

public class fV {
   private final boolean q;
   private Op RF;
   private boolean xK;
   private List Dw = new ArrayList();
   private wh Uv = null;
   private CW oW = null;
   private int gO = 32;
   private boolean nf = false;
   private OQ.eo gP = null;
   private fV.eo za = fV.eo.q;
   private fV.CU lm = null;
   private CW zz = null;
   private String JY = null;
   private String HF = null;
   private String LK = null;

   private fV(boolean var1, Op var2) {
      this.q = var1;
      this.RF = var2;
   }

   static fV q(Op var0) {
      return new fV(true, var0);
   }

   static fV RF(Op var0) {
      return new fV(false, var0);
   }

   void q(CW var1) {
      this.Dw.add(var1);
   }

   void RF(CW var1) {
      if (var1 instanceof wh) {
         this.Uv = (wh)var1;
      } else {
         throw new IllegalStateException(Strings.f("Not a valid Load Store operand:  %s (%s)", var1, var1.getClass()));
      }
   }

   void xK(Op var1) {
      if ((var1.q().endsWith("B") || var1.q().endsWith("BT"))
         && !var1.q().equals("LDRAB")
         && !var1.q().equals("LD64B")
         && !var1.q().endsWith("MDB")
         && !var1.q().endsWith("MIB")) {
         this.gO = 8;
      } else if ((var1.q().endsWith("H") || var1.q().endsWith("HT")) && !var1.q().endsWith("PUSH")) {
         this.gO = 16;
      } else if (var1.q().endsWith("W")) {
         this.gO = 32;
      } else if (Strings.isContainedIn(var1.q(), "VLDR", "VSTR")) {
         if ("16".equals(var1.oW())) {
            this.gO = 16;
         } else {
            this.gO = -1;
         }
      } else {
         this.gO = -1;
      }
   }

   public void q(int var1) {
      this.gO = var1;
   }

   void q(String var1) {
      if (var1.endsWith("IB")) {
         this.gP = OQ.eo.Dw;
      } else if (Strings.endsWith(var1, "DB", "PUSH")) {
         this.gP = OQ.eo.xK;
      } else if (var1.endsWith("DA")) {
         this.gP = OQ.eo.RF;
      } else {
         this.gP = OQ.eo.q;
      }
   }

   void q(boolean var1) {
      this.xK = var1;
   }

   public boolean q() {
      return this.xK;
   }

   public int RF() {
      if (this.Dw.isEmpty()) {
         throw new IllegalStateException("Illegal or corrupted Load/Store data");
      } else {
         CW var1 = (CW)this.Dw.get(0);
         if (var1 instanceof BY) {
            var1 = ((BY)this.Dw.get(0)).RF();
            return var1 instanceof RI ? xB.q((RI)var1) : k.q(this.RF);
         } else {
            return var1 instanceof RI ? xB.q((RI)var1) : ((CW)this.Dw.get(0)).getOperandBitsize();
         }
      }
   }

   public CW RF(int var1) {
      return (CW)this.Dw.get(var1);
   }

   public int xK() {
      return this.Dw.size();
   }

   public wh Dw() {
      return this.Uv;
   }

   public int Uv() {
      return this.gO == -1 ? this.RF() : this.gO;
   }

   void RF(boolean var1) {
      this.nf = var1;
   }

   public boolean oW() {
      return this.nf;
   }

   void xK(CW var1) {
      this.oW = var1;
   }

   public CW gO() {
      return this.oW;
   }

   public boolean nf() {
      return this.q;
   }

   public CW[] gP() {
      return (CW[])this.Dw.toArray(new CW[this.Dw.size()]);
   }

   public OQ.eo za() {
      return this.gP;
   }

   public fV.eo lm() {
      return this.za;
   }

   void q(fV.eo var1) {
      this.za = var1;
   }

   void q(String var1, CW var2) {
      this.lm = this.Uv(var1);
      this.zz = var2;
   }

   private fV.CU Uv(String var1) {
      String var2 = var1.substring(2, 5);
      if (var2.endsWith("MA")) {
         var2 = var2 + "X";
      } else if (var2.endsWith("MI")) {
         var2 = var2 + "N";
      }

      return fV.CU.valueOf(var2);
   }

   public fV.CU zz() {
      return this.lm;
   }

   public CW JY() {
      return this.zz;
   }

   void RF(String var1) {
      this.JY = Character.toString(var1.charAt(var1.length() - 1));
   }

   public String HF() {
      return this.JY;
   }

   void xK(String var1) {
      this.HF = var1;
   }

   public String LK() {
      return this.HF;
   }

   void Dw(String var1) {
      this.LK = var1;
   }

   public String io() {
      return this.LK;
   }

   public boolean qa() {
      return this.LK() != null || this.io() != null;
   }

   public boolean Hk() {
      return this.HF() == null && this.LK() == null;
   }

   boolean Me() {
      return !this.Dw.isEmpty() && this.Uv != null;
   }

   public static enum CU {
      q,
      RF,
      xK,
      Dw,
      Uv,
      oW,
      gO,
      nf;
   }

   public static enum eo {
      q,
      RF,
      xK,
      Dw,
      Uv,
      oW;
   }
}
