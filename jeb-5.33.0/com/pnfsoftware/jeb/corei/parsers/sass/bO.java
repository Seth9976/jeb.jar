package com.pnfsoftware.jeb.corei.parsers.sass;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.code.cuda.ICudaFatbinUnit;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Ser
public class bO extends AbstractBinaryUnit implements ICudaFatbinUnit {
   @SerId(1)
   List pC;

   public bO(IInput var1, String var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(null, var1, "cuda_fatbin", var2, var3, var4, var5);
   }

   @Override
   protected boolean processInternal() {
      byte[] var1;
      try (InputStream var2 = this.getInput().getStream()) {
         var1 = IO.readInputStream(var2);
      } catch (IOException var11) {
         return false;
      }

      try {
         Sv var12 = new Sv(var1);
         this.pC = var12.pC();
         int var3 = 0;

         for (Av var5 : this.pC) {
            BytesInput var6 = new BytesInput(var5.getData());
            IUnit var7 = this.getUnitProcessor().process("cubin." + var3 + ".sm_" + var5.getCudaArch(), var6, this);
            this.addChild(var7);
            var3++;
         }

         return true;
      } catch (Exception var10) {
         return false;
      }
   }

   @Override
   public List getCubinEntries() {
      return this.pC == null ? null : Collections.unmodifiableList(this.pC);
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(super.getDescription());
      var1.append("\n");
      if (this.pC == null) {
         Strings.ff(var1, S.L("Not parsed or parsing error\n"));
      } else {
         int var2 = 0;

         for (Av var4 : this.pC) {
            Strings.ff(var1, "#%d: %s", var2, var4.pC(true));
            String var5 = uX.pC(var4.getCudaArch());
            var1.append(" (");
            var1.append(var5);
            if (var4.getKind() == 2 && var4.getCudaArch() < 70) {
               var1.append(" - ");
               var1.append(S.L("pre-Volta code will not be parsed)"));
            }

            var1.append(")");
            var1.append("\n");
            var2++;
         }
      }

      return var1.toString();
   }

   @Override
   public byte[] getIconData() {
      try {
         byte[] var2;
         try (InputStream var1 = CudaFatbinIdentifier.class.getResourceAsStream("assets/nvidia_logo1.png")) {
            var2 = IO.readInputStream(var1);
         }

         return var2;
      } catch (IOException var6) {
         return null;
      }
   }
}
