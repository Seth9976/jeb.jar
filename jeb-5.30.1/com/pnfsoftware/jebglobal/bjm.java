package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexCallSite;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Arrays;
import java.util.List;

@Ser
public class bjm extends com.pnfsoftware.jeb.corei.parsers.dex.CU implements IDexCallSite, bjx {
   @SerId(2)
   private int RF;
   @SerId(3)
   private bia[] xK;

   public bjm(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, int var2, bia[] var3) {
      super(var1);
      this.RF = var2;
      this.xK = var3;
   }

   @Override
   public int getIndex() {
      return this.RF;
   }

   @Override
   public List getCallSiteValues() {
      return Arrays.asList(this.xK);
   }

   public bia q(int var1) {
      return this.xK[var1];
   }

   @Override
   public int getLinkerMethodHandleIndex() {
      return this.xK[0].getMethodHandleIndex();
   }

   @Override
   public int getDynamicMethodNameIndex() {
      return this.xK[1].getStringIndex();
   }

   @Override
   public int getDynamicMethodPrototypeIndex() {
      return this.xK[2].getMethodTypeIndex();
   }

   public bjq RF() {
      return this.q.za(this.getLinkerMethodHandleIndex());
   }

   public bjs xK() {
      return this.q.xK(this.getDynamicMethodNameIndex());
   }

   public bjr Dw() {
      return this.q.Uv(this.getDynamicMethodPrototypeIndex());
   }

   @Override
   public String getAddress(boolean var1) {
      return null;
   }

   @Override
   public String getName(boolean var1) {
      return "call_site_" + this.RF;
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
      bjq var2 = this.q.za(this.xK[0].getMethodHandleIndex());
      bjs var3 = this.q.xK(this.xK[1].getStringIndex());
      bjr var4 = this.q.Uv(this.xK[2].getMethodTypeIndex());
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
