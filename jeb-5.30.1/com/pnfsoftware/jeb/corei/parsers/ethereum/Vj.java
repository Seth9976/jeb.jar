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
import com.pnfsoftware.jebglobal.aae;

@Ser
public class Vj extends AbstractAnalyzerExtension {
   private static final ILogger q = GlobalLog.getLogger(Vj.class);

   @Override
   public void typeManagerInitialized(ITypeManager var1) {
      IPrimitiveTypeManager var2 = var1.getPrimitives();
      var2.addPrimitive("address", 20, PrimitiveCategory.UNSIGNED);
   }

   @Override
   public ChainedOperationResult preprocessImage(int var1) {
      if (var1 == 0) {
         this.q();
      }

      return ChainedOperationResult.TRUE_CONTINUE;
   }

   private void q() {
      Assert.a(this.gca.getContainer() instanceof PY);
      PY var1 = (PY)this.gca.getContainer();
      eM var2 = var1.gO;

      for (vX var4 : var2.Dw) {
         ((aae)this.gca).q((long)var4.zz, 0);
      }

      for (qx var10 : var2.za.values()) {
         if (var10.lm != null && var10.lm.length >= 1) {
            String var5 = var10.lm[0];
            this.getUnit().getCodeModel().getLabelManager().setLabel(var10.Dw(), var5, true, false, false);
         }
      }

      if (var2.LK != null) {
         INativeType var9 = this.gca.getTypeManager().getType("unsigned char");
         IArrayType var11 = this.gca.getTypeManager().createArray(var9, var2.io);
         long var12 = var1.convertFileOffsetToRelativeAddress(var2.LK.intValue());
         INativeDataItem var7 = this.gca.defineData(var12, var11);
         var7.setName("solcMetadata");
      }
   }
}
