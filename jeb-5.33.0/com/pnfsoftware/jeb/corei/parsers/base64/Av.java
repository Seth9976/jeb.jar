package com.pnfsoftware.jeb.corei.parsers.base64;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.IDataProvider;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.input.LazyInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractInteractiveBinaryUnit;
import com.pnfsoftware.jeb.core.units.IArchiveUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.UnknownBinaryUnit;
import com.pnfsoftware.jeb.util.encoding.Base64;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.io.IOException;
import java.io.InputStream;

@Ser
public class Av extends AbstractInteractiveBinaryUnit implements IDataProvider, IArchiveUnit {
   private static final ILogger pC = GlobalLog.getLogger(Av.class);

   public Av(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super("text/plain", var2, "base64", var1, var3, var4, var5);
   }

   @Override
   public byte[] getDataBytes(String var1, int var2) {
      try {
         byte[] var5;
         try (InputStream var3 = this.getInput().getStream()) {
            byte[] var4 = IO.readInputStream(var3);
            var5 = Base64.decode(var4);
         }

         return var5;
      } catch (IOException var8) {
         pC.error(S.L("Failed decoding using base64 decoder"));
         return new byte[0];
      }
   }

   @Override
   public long getDataSize(String var1, int var2) {
      return 0L;
   }

   @Override
   protected boolean processInternal() {
      LazyInput var1 = new LazyInput(this, "decoded", -1L, 0);
      UnknownBinaryUnit var2 = new UnknownBinaryUnit("decoded", var1, this.getUnitProcessor(), this, this.getPropertyDefinitionManager());
      this.addChild(var2);
      return true;
   }
}
