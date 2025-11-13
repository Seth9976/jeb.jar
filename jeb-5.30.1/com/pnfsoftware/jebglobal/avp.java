package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.CodeDocumentPart;
import com.pnfsoftware.jeb.core.output.code.coordinates.NativeCoordinates;
import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Collection;

public class avp extends CodeDocumentPart implements and {
   private avo RF;
   public static final boolean q = false;

   public avp(long var1, avo var3) {
      super(var1);
      this.RF = var3;
   }

   public void q(IERoutineContext var1) {
      for (AddressableInstruction var3 : var1.getCfg().addressableInstructions()) {
         IEStatement var4 = (IEStatement)var3.getInstruction();
         this.q((int)var3.getOffset());
         this.append(":  ");
         ((ane)var4).q(this);
         Long var5 = var4.getPrimaryLowerLevelAddress();
         this.eol(var5 == null ? null : new NativeCoordinates(var5));
         if (var5 != null && var1.getNativeContext() instanceof abg) {
            abg var6 = (abg)var1.getNativeContext();
            String var7 = var6.getFullComment(Strings.ff("%Xh", var5));
            if (var7 != null) {
               int var8 = this.getCurrentLineLength();
               int var9 = 2 + Math.max(0, 98 - var8);
               this.space(var9);
               this.appendComment("// " + var7);
            }
         }
      }
   }

   @Override
   public boolean q() {
      return this.RF.RF;
   }

   @Override
   public boolean RF() {
      return this.RF.xK;
   }

   @Override
   public boolean xK() {
      return this.RF.Dw;
   }

   @Override
   public void q(IEGeneric var1) {
      ((ane)var1).q(this);
   }

   @Override
   public void q(Collection var1) {
      this.bracket();
      int var2 = 0;

      for (IEGeneric var4 : var1) {
         if (var2 >= 1) {
            this.append(", ");
         }

         ((ane)var4).q(this);
         var2++;
      }

      this.bracketClose();
   }

   @Override
   public void append(String var1, ItemClassIdentifiers var2) {
      this.appendAndRecord(var1, var2);
   }

   @Override
   public void q(int var1) {
      this.appendAndRecord(Strings.ff("%04X", var1), ItemClassIdentifiers.ADDRESS);
   }

   @Override
   public void RF(IEGeneric var1) {
      if (this.RF.Dw) {
         String var2 = null;
         if (var1.getType() != null) {
            String var3 = var1.getType().toString();
            if (!var3.equals("?")) {
               var2 = "<<" + var3 + ">>";
            }
         }

         if (var2 != null) {
            this.appendAndRecord(var2, ItemClassIdentifiers.TYPE);
         }
      }
   }
}
