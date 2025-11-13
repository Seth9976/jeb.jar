package com.pnfsoftware.jeb.corei.parsers.gzip;

import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.IDataProvider;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IArchiveUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.impl.DataContainerUnit;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.TreeMap;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipParameters;

@Ser
public class Av extends AbstractBinaryUnit implements IDataProvider, IArchiveUnit {
   private static final ILogger pC = GlobalLog.getLogger(Av.class);

   public Av(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super("application/gzip", var2, "gzip", var1, var3, var4, var5);
   }

   @Override
   public byte[] getDataBytes(String var1, int var2) {
      return new byte[0];
   }

   @Override
   public long getDataSize(String var1, int var2) {
      return 0L;
   }

   @Override
   protected boolean processInternal() {
      TreeMap var1 = new TreeMap();

      try {
         GzipCompressorInputStream var2 = new GzipCompressorInputStream(this.getInput().getStream());

         try {
            int var3 = 0;

            while (true) {
               try {
                  GzipParameters var4 = var2.getMetaData();
                  String var5 = var4.getFilename();
                  if (var5 == null) {
                     var5 = this.getName();
                     int var6 = var5.lastIndexOf(46);
                     if (var6 > 0) {
                        var5 = var5.substring(0, var6);
                     }
                  }

                  if (var1.containsKey(var5)) {
                     var5 = var5 + "." + var3;
                  }

                  ByteArrayOutputStream var16 = new ByteArrayOutputStream();
                  int var8 = 0;

                  int var7;
                  for (byte[] var9 = new byte[16384]; (var7 = var2.read(var9, 0, var9.length)) != -1; var8++) {
                     var16.write(var9, 0, var7);
                  }

                  if (var8 == 0) {
                     break;
                  }

                  var16.flush();
                  byte[] var10 = var16.toByteArray();
                  DataContainerUnit.Entry var11 = DataContainerUnit.Entry.create(var5, var10);
                  Maps.putNoOverwrite(var1, var5, var11);
                  var3++;
               } catch (IOException var13) {
                  pC.catching(var13);
                  break;
               }
            }
         } catch (Throwable var14) {
            try {
               var2.close();
            } catch (Throwable var12) {
               var14.addSuppressed(var12);
            }

            throw var14;
         }

         var2.close();
      } catch (IOException var15) {
         pC.catching(var15);
         return false;
      }

      DataContainerUnit.register(this, var1.values());
      return true;
   }
}
