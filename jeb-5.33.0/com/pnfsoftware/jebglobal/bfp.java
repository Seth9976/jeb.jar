package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClassData;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class bfp implements IDexClassData {
   @SerId(1)
   private bfz[] pC;
   @SerId(2)
   private bfz[] A;
   @SerId(3)
   private bgd[] kS;
   @SerId(4)
   private bgd[] wS;

   public bfp(bfz[] var1, bfz[] var2, bgd[] var3, bgd[] var4) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
   }

   @Override
   public List getStaticFields() {
      return ArrayUtil.asView(this.pC);
   }

   @Override
   public List getInstanceFields() {
      return ArrayUtil.asView(this.A);
   }

   public List pC() {
      return ArrayUtil.asView(this.pC, this.A);
   }

   public int A() {
      return this.pC.length + this.A.length;
   }

   @Override
   public List getDirectMethods() {
      return ArrayUtil.asView(this.kS);
   }

   @Override
   public List getVirtualMethods() {
      return ArrayUtil.asView(this.wS);
   }

   public List kS() {
      return ArrayUtil.asView(this.kS, this.wS);
   }

   public int wS() {
      return this.kS.length + this.wS.length;
   }
}
