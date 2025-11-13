package com.pnfsoftware.jeb.corei.parsers.dex.vdex;

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
public class eo extends AbstractBinaryUnit {
   private static final ILogger q = GlobalLog.getLogger(eo.class);
   @SerId(1)
   private Integer RF;

   protected eo(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(null, var2, "vdex", var1, var3, var4, var5);
   }

   @Override
   protected boolean processInternal() {
      ByteBuffer var1 = this.getInput().getHeader();
      if (var1.remaining() < 8) {
         return false;
      } else {
         try {
            this.RF = DexUtil.getVersion(this);
         } catch (ParseException var3) {
            q.catching(var3);
         }

         return true;
      }
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(super.getDescription());
      var1.append("\n");
      Strings.ff(var1, "Android VDEX file, version %s:\n", this.RF == null ? "?" : this.RF.toString());
      Strings.ff(var1, "\n");
      Strings.ff(var1, "!! JEB does not provide support for parsing VDEX files !!\n");
      Strings.ff(var1, "\n");
      Strings.ff(var1, "Please use a third-party tool to extract Dex files.\n");
      return var1.toString();
   }
}
