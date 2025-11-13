package com.pnfsoftware.jeb.core.units.code.android.controlflow;

import com.pnfsoftware.jeb.util.format.Strings;

public class IrregularFlowData {
   long first;
   long last;
   long target;

   public IrregularFlowData(long var1, long var3, long var5) {
      this.first = var1;
      this.last = var3;
      this.target = var5;
   }

   public long getFirstAddress() {
      return this.first;
   }

   public long getLastAddress() {
      return this.last;
   }

   public long getTargetAddress() {
      return this.target;
   }

   @Override
   public String toString() {
      return Strings.ff("[%Xh,%Xh]->%Xh", this.getFirstAddress(), this.getLastAddress(), this.getTargetAddress());
   }
}
