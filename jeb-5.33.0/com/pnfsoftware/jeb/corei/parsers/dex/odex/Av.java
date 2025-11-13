package com.pnfsoftware.jeb.corei.parsers.dex.odex;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.IOptimizedDexUnit;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

@Ser
public class Av extends AbstractBinaryUnit implements IOptimizedDexUnit {
   @SerId(1)
   private int pC;
   @SerId(2)
   private int A;
   @SerId(3)
   private int kS;
   @SerId(4)
   private int wS;
   @SerId(5)
   private int UT;
   @SerId(6)
   private int E;
   @SerId(7)
   private int sY;
   @SerId(8)
   private int ys;
   @SerId(9)
   private IDexUnit ld;

   @SerCustomInitPostGraph
   private void pC() {
      if (this.ld == null) {
         List var1 = this.getChildren();
         if (var1.size() >= 1 && var1.get(0) instanceof IDexUnit) {
            this.ld = (IDexUnit)var1.get(0);
         }
      }
   }

   protected Av(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(null, var2, "odex", var1, var3, var4, var5);
   }

   @Override
   protected boolean processInternal() {
      try {
         ByteBuffer var1 = this.getInput().getHeader();
         if (var1.remaining() <= 40) {
            return false;
         } else {
            this.pC = var1.getInt(8);
            this.A = var1.getInt(12);
            this.kS = var1.getInt(16);
            this.wS = var1.getInt(20);
            this.UT = var1.getInt(24);
            this.E = var1.getInt(28);
            this.sY = var1.getInt(32);
            this.ys = var1.getInt(36);
            if ((this.pC & -65536) != 0) {
               this.pC = Integer.reverseBytes(this.pC);
               this.A = Integer.reverseBytes(this.A);
               this.kS = Integer.reverseBytes(this.kS);
               this.wS = Integer.reverseBytes(this.wS);
               this.UT = Integer.reverseBytes(this.UT);
               this.E = Integer.reverseBytes(this.E);
               this.sY = Integer.reverseBytes(this.sY);
               this.ys = Integer.reverseBytes(this.ys);
            }

            BytesInput var2 = new BytesInput(this.getInput(), this.pC, this.A);
            this.ld = (IDexUnit)this.getUnitProcessor().process("inner_dex", var2, this, "dex");
            this.addChild(this.ld);
            return true;
         }
      } catch (IOException var3) {
         this.logError(false, S.L("An error occurred while processing the ODEX file"));
         JebCoreService.notifySilentExceptionToClient(var3);
         return false;
      }
   }

   @Override
   public IDexUnit getDex() {
      return this.ld;
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(super.getDescription());
      var1.append("\n");
      var1.append("ODEX header:\n");
      Strings.ff(var1, "- Optimized Dex: offset= %Xh, size= %Xh\n", this.pC, this.A);
      Strings.ff(var1, "- Dependency table: offset= %Xh, size= %Xh\n", this.kS, this.wS);
      Strings.ff(var1, "- Optimized data tables: offset= %Xh, size= %Xh\n", this.UT, this.E);
      return var1.toString();
   }
}
