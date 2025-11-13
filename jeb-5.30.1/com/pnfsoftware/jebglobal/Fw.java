package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.IInputRecord;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class Fw implements IInputRecord {
   @SerId(1)
   long q;
   @SerId(2)
   long RF;
   @SerId(3)
   byte[] xK;
   @SerId(4)
   int Dw;
   @SerId(5)
   List Uv;

   public Fw(long var1, long var3, byte[] var5, List var6) {
      this.q = var1;
      this.RF = var3;
      this.xK = var5;
      this.Uv = new ArrayList(var6);
   }

   @Override
   public long getSize() {
      return this.q;
   }

   public long q() {
      return this.RF;
   }

   @Override
   public byte[] getContentsSha256() {
      return this.xK;
   }

   public int RF() {
      return this.Dw;
   }

   @Override
   public int getSeenCount() {
      return 1 + this.Dw;
   }
}
