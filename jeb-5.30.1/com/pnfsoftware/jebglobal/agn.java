package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.coordinates.NativeCoordinates;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class agn extends afh implements ICLabel {
   @SerId(1)
   long RF;
   @SerId(2)
   String xK;

   agn(long var1, String var3) {
      if (var3 == null) {
         throw new IllegalArgumentException();
      } else {
         this.RF = var1;
         this.xK = var3;
      }
   }

   public agn RF() {
      return this;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (int)(this.RF ^ this.RF >>> 32);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         agn var2 = (agn)var1;
         return this.RF == var2.RF;
      }
   }

   @Override
   public long getOffset() {
      return this.RF;
   }

   @Override
   public String getName() {
      return this.xK;
   }

   @Override
   public List getSubElements() {
      return ahf.q();
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      return false;
   }

   @Override
   public void generate(COutputSink var1, boolean var2) {
      this.q(var1);
      String var3 = this.xK;
      long var4 = 0L;
      IDynamicContentManager var6 = var1.getDynamicContentManager();
      if (var6 != null && this.getPhysicalOffset() != null) {
         NativeCoordinates var7 = new NativeCoordinates(this.getPhysicalOffset());
         var3 = var6.getLabelName(var7);
         var4 = var6.getLabelItemId(var7);
         if (var3 == null) {
            var3 = this.xK;
         }
      }

      if (var2) {
         var3 = var3 + ":";
      }

      var1.appendAndRecord(var3, ItemClassIdentifiers.LABEL, var4, var2 ? 1 : 0);
      this.RF(var1);
   }

   @Override
   public void generate(COutputSink var1) {
      this.generate(var1, true);
   }

   @Override
   public CElementType getElementType() {
      return CElementType.Label;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      var1.setControlWord(CMethodState.ControlWord.GOTO_NEXT_INS);
      return null;
   }

   @Override
   public String toString() {
      return this.getName() + ":";
   }
}
