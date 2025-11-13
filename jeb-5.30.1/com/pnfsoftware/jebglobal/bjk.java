package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClassData;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class bjk implements IDexClassData {
   @SerId(1)
   private bju[] q;
   @SerId(2)
   private bju[] RF;
   @SerId(3)
   private bjy[] xK;
   @SerId(4)
   private bjy[] Dw;

   public bjk(bju[] var1, bju[] var2, bjy[] var3, bjy[] var4) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
   }

   @Override
   public List getStaticFields() {
      return ArrayUtil.asView(this.q);
   }

   @Override
   public List getInstanceFields() {
      return ArrayUtil.asView(this.RF);
   }

   public List q() {
      return ArrayUtil.asView(this.q, this.RF);
   }

   public int RF() {
      return this.q.length + this.RF.length;
   }

   @Override
   public List getDirectMethods() {
      return ArrayUtil.asView(this.xK);
   }

   @Override
   public List getVirtualMethods() {
      return ArrayUtil.asView(this.Dw);
   }

   public List xK() {
      return ArrayUtil.asView(this.xK, this.Dw);
   }

   public int Dw() {
      return this.xK.length + this.Dw.length;
   }
}
