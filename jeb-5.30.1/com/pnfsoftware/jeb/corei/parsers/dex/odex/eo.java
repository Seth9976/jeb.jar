package com.pnfsoftware.jeb.corei.parsers.dex.odex;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.input.SubInput;
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
public class eo extends AbstractBinaryUnit implements IOptimizedDexUnit {
   @SerId(1)
   private int q;
   @SerId(2)
   private int RF;
   @SerId(3)
   private int xK;
   @SerId(4)
   private int Dw;
   @SerId(5)
   private int Uv;
   @SerId(6)
   private int oW;
   @SerId(7)
   private int gO;
   @SerId(8)
   private int nf;
   @SerId(9)
   private IDexUnit gP;

   @SerCustomInitPostGraph
   private void q() {
      if (this.gP == null) {
         List var1 = this.getChildren();
         if (var1.size() >= 1 && var1.get(0) instanceof IDexUnit) {
            this.gP = (IDexUnit)var1.get(0);
         }
      }
   }

   protected eo(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(null, var2, "odex", var1, var3, var4, var5);
   }

   @Override
   protected boolean processInternal() {
      try {
         ByteBuffer var1 = this.getInput().getHeader();
         if (var1.remaining() <= 40) {
            return false;
         } else {
            this.q = var1.getInt(8);
            this.RF = var1.getInt(12);
            this.xK = var1.getInt(16);
            this.Dw = var1.getInt(20);
            this.Uv = var1.getInt(24);
            this.oW = var1.getInt(28);
            this.gO = var1.getInt(32);
            this.nf = var1.getInt(36);
            if ((this.q & -65536) != 0) {
               this.q = Integer.reverseBytes(this.q);
               this.RF = Integer.reverseBytes(this.RF);
               this.xK = Integer.reverseBytes(this.xK);
               this.Dw = Integer.reverseBytes(this.Dw);
               this.Uv = Integer.reverseBytes(this.Uv);
               this.oW = Integer.reverseBytes(this.oW);
               this.gO = Integer.reverseBytes(this.gO);
               this.nf = Integer.reverseBytes(this.nf);
            }

            SubInput var2 = new SubInput(this.getInput(), this.q, this.RF);
            this.gP = (IDexUnit)this.getUnitProcessor().process("inner_dex", var2, this, "dex");
            this.addChild(this.gP);
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
      return this.gP;
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(super.getDescription());
      var1.append("\n");
      var1.append("ODEX header:\n");
      Strings.ff(var1, "- Optimized Dex: offset= %Xh, size= %Xh\n", this.q, this.RF);
      Strings.ff(var1, "- Dependency table: offset= %Xh, size= %Xh\n", this.xK, this.Dw);
      Strings.ff(var1, "- Optimized data tables: offset= %Xh, size= %Xh\n", this.Uv, this.oW);
      return var1.toString();
   }
}
