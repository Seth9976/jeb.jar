package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.IInputRecord;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class Pe implements IInputRecord {
   @SerId(1)
   long pC;
   @SerId(2)
   long A;
   @SerId(3)
   byte[] kS;
   @SerId(4)
   int wS;
   @SerId(5)
   List UT;

   public Pe(long var1, long var3, byte[] var5, List var6) {
      this.pC = var1;
      this.A = var3;
      this.kS = var5;
      this.UT = new ArrayList(var6);
   }

   @Override
   public long getSize() {
      return this.pC;
   }

   @Override
   public byte[] getContentsSha256() {
      return this.kS;
   }

   @Override
   public int getSeenCount() {
      return 1 + this.wS;
   }
}
