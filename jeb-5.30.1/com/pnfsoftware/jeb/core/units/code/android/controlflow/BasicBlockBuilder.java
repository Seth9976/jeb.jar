package com.pnfsoftware.jeb.core.units.code.android.controlflow;

import com.pnfsoftware.jeb.core.units.code.ILocatedInstruction;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class BasicBlockBuilder {
   @SerId(1)
   public List insns = new ArrayList();
   @SerId(2)
   public List dst_offsets = new ArrayList();
   @SerId(3)
   public List irrdst_offsets = new ArrayList();

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      if (this.insns.isEmpty()) {
         var1.append("EMPTY:");
      } else {
         Strings.ff(var1, "0x%X:", ((ILocatedInstruction)this.insns.get(0)).getOffset());
      }

      Strings.ff(var1, "cnt=%d,dstoff=%s,idstoff=%s", this.insns.size(), this.dst_offsets, this.irrdst_offsets);
      return var1.toString();
   }
}
