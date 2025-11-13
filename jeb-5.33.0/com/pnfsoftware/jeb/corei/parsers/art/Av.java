package com.pnfsoftware.jeb.corei.parsers.art;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;
import java.text.ParseException;

@Ser
public class Av extends AbstractBinaryUnit {
   private static final ILogger pC = GlobalLog.getLogger(Av.class);
   @SerId(1)
   private Integer A;

   protected Av(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(null, var2, "art", var1, var3, var4, var5);
   }

   @Override
   protected boolean processInternal() {
      ByteBuffer var1 = this.getInput().getHeader();
      if (var1.remaining() < 8) {
         return false;
      } else {
         try {
            this.A = DexUtil.getVersion(this);
         } catch (ParseException var3) {
            pC.catching(var3);
         }

         return true;
      }
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(super.getDescription());
      Strings.ff(var1, "\n");
      Strings.ff(var1, S.L("Android ART file, version %s:\n"), this.A == null ? "?" : this.A.toString());
      Strings.ff(var1, "\n");
      Strings.ff(var1, S.L("JEB does not provide support for parsing ART files!\n"));
      Strings.ff(var1, S.L("Please use a third-party tool to extract relevant data, and process them with JEB.\n"));
      return var1.toString();
   }
}
