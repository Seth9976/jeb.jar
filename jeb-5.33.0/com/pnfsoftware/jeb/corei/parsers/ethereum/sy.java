package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AbstractAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IArrayType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrimitiveTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.PrimitiveCategory;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jebglobal.a;

@Ser
public class sy extends AbstractAnalyzerExtension {
   private static final ILogger pC = GlobalLog.getLogger(sy.class);

   @Override
   public void typeManagerInitialized(ITypeManager var1) {
      IPrimitiveTypeManager var2 = var1.getPrimitives();
      var2.addPrimitive("address", 20, PrimitiveCategory.UNSIGNED);
   }

   @Override
   public ChainedOperationResult preprocessImage(int var1) {
      if (var1 == 0) {
         this.pC();
      }

      return ChainedOperationResult.TRUE_CONTINUE;
   }

   private void pC() {
      Assert.a(this.gca.getContainer() instanceof yt);
      yt var1 = (yt)this.gca.getContainer();
      ma var2 = var1.E;

      for (Mh var4 : var2.wS) {
         ((a)this.gca).pC((long)var4.UT, 0);
      }

      for (eW var10 : var2.gp.values()) {
         if (var10.ys != null && var10.ys.length >= 1) {
            String var5 = var10.ys[0];
            this.getUnit().getCodeModel().getLabelManager().setLabel(var10.A(), var5, true, false, false);
         }
      }

      if (var2.vP != null) {
         INativeType var9 = this.gca.getTypeManager().getType("unsigned char");
         IArrayType var11 = this.gca.getTypeManager().createArray(var9, var2.xC);
         long var12 = var1.convertFileOffsetToRelativeAddress(var2.vP.intValue());
         INativeDataItem var7 = this.gca.defineData(var12, var11);
         var7.setName("solcMetadata");
      }
   }
}
