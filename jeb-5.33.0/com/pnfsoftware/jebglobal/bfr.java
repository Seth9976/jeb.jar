package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexCallSite;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Arrays;
import java.util.List;

@Ser
public class bfr extends com.pnfsoftware.jeb.corei.parsers.dex.Sv implements IDexCallSite, bgc {
   @SerId(2)
   private int A;
   @SerId(3)
   private beg[] kS;

   public bfr(com.pnfsoftware.jeb.corei.parsers.dex.vi var1, int var2, beg[] var3) {
      super(var1);
      this.A = var2;
      this.kS = var3;
   }

   @Override
   public int getIndex() {
      return this.A;
   }

   @Override
   public List getCallSiteValues() {
      return Arrays.asList(this.kS);
   }

   public beg pC(int var1) {
      return this.kS[var1];
   }

   @Override
   public int getLinkerMethodHandleIndex() {
      return this.kS[0].getMethodHandleIndex();
   }

   @Override
   public int getDynamicMethodNameIndex() {
      return this.kS[1].getStringIndex();
   }

   @Override
   public int getDynamicMethodPrototypeIndex() {
      return this.kS[2].getMethodTypeIndex();
   }

   public bfv A() {
      return this.pC.gp(this.getLinkerMethodHandleIndex());
   }

   public bfx kS() {
      return this.pC.kS(this.getDynamicMethodNameIndex());
   }

   public bfw wS() {
      return this.pC.UT(this.getDynamicMethodPrototypeIndex());
   }

   @Override
   public String getAddress(boolean var1) {
      return null;
   }

   @Override
   public String getName(boolean var1) {
      return "call_site_" + this.A;
   }

   @Override
   public String getSignature(boolean var1) {
      return null;
   }

   @Override
   public String getSignature(boolean var1, boolean var2) {
      return null;
   }

   @Override
   public String generate(boolean var1) {
      bfv var2 = this.pC.gp(this.kS[0].getMethodHandleIndex());
      bfx var3 = this.pC.kS(this.kS[1].getStringIndex());
      bfw var4 = this.pC.UT(this.kS[2].getMethodTypeIndex());
      return Strings.ff("%s,%s,%s", var2.generate(var1), var3.getValue(true), var4.generate(true));
   }

   @Override
   public boolean isInternal() {
      return true;
   }

   @Override
   public boolean isArtificial() {
      return false;
   }

   @Override
   public int getGenericFlags() {
      return 0;
   }
}
