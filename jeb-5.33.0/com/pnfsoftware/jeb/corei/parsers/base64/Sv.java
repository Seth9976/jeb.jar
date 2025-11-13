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
import com.pnfsoftware.jeb.core.units.WellKnownUnitTypes;
import com.pnfsoftware.jeb.core.units.impl.LazyDataContainerUnit;
import com.pnfsoftware.jeb.util.encoding.Base64;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.cku;
import java.io.IOException;
import java.io.InputStream;

@Ser
public class Sv extends AbstractInteractiveBinaryUnit implements IDataProvider, IArchiveUnit {
   private static final ILogger pC = GlobalLog.getLogger(Sv.class);
   @SerId(1)
   private String A = "text/plain";
   @SerId(2)
   private String kS = "US-ASCII";
   @SerId(3)
   private int wS = "data:".length();

   public Sv(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super("text/plain", var2, "dataUri", var1, var3, var4, var5);
   }

   @Override
   public byte[] getDataBytes(String var1, int var2) {
      try {
         byte[] var11;
         try (InputStream var3 = this.getInput().getStream()) {
            var3.skip("data:".length() + this.wS + 1);
            byte[] var4 = IO.readInputStream(var3);
            String var5 = new String(var4, "US-ASCII");
            byte[] var6 = Base64.decode(var5);
            if (this.A.startsWith("text/") && !this.kS.isEmpty() && !"US-ASCII".equals(this.kS) && !"UTF-8".equals(this.kS)) {
               String var7 = new String(var6, this.kS);
               var6 = var7.getBytes("UTF-8");
            }

            var11 = var6;
         }

         return var11;
      } catch (IOException var10) {
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
      LazyDataContainerUnit.Entry var1 = new Sv.Av().pC();
      LazyInput var2 = new LazyInput(this, var1.getName(), -1L, 0);
      UnknownBinaryUnit var3 = new UnknownBinaryUnit(var1.getName(), var2, this.getUnitProcessor(), this, this.getPropertyDefinitionManager());
      var3.setMimeType(this.A);
      String var4 = WellKnownUnitTypes.fromMimeType(this.A);
      if (var4 != null) {
         var3.setHintWantedType(var4);
      }

      this.addChild(var3);
      return true;
   }

   class Av extends cku {
      public LazyDataContainerUnit.Entry pC() {
         try {
            LazyDataContainerUnit.Entry var10;
            try (InputStream var1 = Sv.this.getInput().getStream()) {
               int var2 = 0;
               byte[] var3 = new byte[300];
               var1.skip("data:".length());

               for (int var4 = var1.read(var3, 0, 300); var2 < var4; var2++) {
                  if (var3[var2] == 59 || var3[var2] == 44) {
                     int var5 = Sv.this.wS == 0 ? 0 : Sv.this.wS + 1;
                     String var6 = new String(var3, var5, var2 - var5, "US-ASCII");
                     if (!var6.equals("base64")) {
                        if (var6.startsWith("charset=")) {
                           Sv.this.kS = var6.substring("charset=".length());
                        } else {
                           Sv.this.A = var6;
                        }
                     }

                     Sv.this.wS = var2;
                     if (var3[var2] == 44) {
                        break;
                     }
                  }
               }

               this.pC(Sv.this.A.replace('/', '_'), -1L);
               var10 = this.pC(Sv.this.A.replace('/', '_'), 0);
            }

            return var10;
         } catch (IOException var9) {
            Sv.pC.catching(var9);
            return null;
         }
      }

      @Override
      public IDataProvider A() {
         return Sv.this;
      }
   }
}
