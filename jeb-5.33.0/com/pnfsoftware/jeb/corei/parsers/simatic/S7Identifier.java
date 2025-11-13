package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.units.AbstractUnitIdentifier;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.io.IO;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

public class S7Identifier extends AbstractUnitIdentifier {
   public S7Identifier() {
      super("simatic_s7", 0.0);
   }

   @Override
   public double getPriority() {
      return 10.0;
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation(
         S.L("S7 Parser"), S.L("Parser for Simatic S7 opaque binary blocks containing S7-300/400 PLC code and data"), "PNF Software", Version.create(0, 1)
      );
   }

   @Override
   public void initialize(IPropertyDefinitionManager var1) {
      super.initialize(var1);
      this.pdm
         .addDefinition("DisassembleCode", PropertyTypeBoolean.create(true), S.L("Disassemble the MC7 code contained in logic blocks (OB, FC, FB, SFC, SFB)"));
      this.pdm
         .addDefinition(
            "MapRawBlocksAtZero", PropertyTypeBoolean.create(false), S.L("Map the raw bytes of S7 blocks at the beginning of the image (address 0)")
         );
      this.pdm
         .addDefinition(
            "GenerateInterfaceDescriptionUnits",
            PropertyTypeBoolean.create(true),
            S.L("Generate additional text units describing interfaces for logic blocks and data blocks")
         );
      this.pdm
         .addDefinition(
            "MapActualBytesForDataBlocks",
            PropertyTypeBoolean.create(true),
            S.L("For data blocks, prefer mapping the actual (current values) bytes insteal of the default (initial values).")
         );
      this.pdm
         .addDefinition(
            "AugmentDisassembly",
            PropertyTypeBoolean.create(true),
            S.L("Augment the disassembly whenever possible, e.g. replace opaque parameter addressing with structure information.")
         );
   }

   @Override
   public boolean acceptAnyInputBytes() {
      return true;
   }

   @Override
   public boolean canIdentify(IInput var1, IUnitCreator var2, String var3, Map var4) {
      if (var3 != null && var3.endsWith(".s7zip") && checkBytes(var1, 0, 80, 75, 3, 4)) {
         try {
            ZipFile var5 = new ZipFile(var1.getChannel());

            boolean var10;
            label119: {
               try {
                  Enumeration var6 = var5.getEntries();

                  while (var6.hasMoreElements()) {
                     ZipArchiveEntry var7 = (ZipArchiveEntry)var6.nextElement();
                     if (!var7.isDirectory() && var5.canReadEntryData(var7) && (var7.getName().endsWith(".s7blk") || var7.getSize() <= 131072L)) {
                        try (InputStream var8 = var5.getInputStream(var7)) {
                           byte[] var9 = IO.readInputStream(var8);
                           if (uX.pC(var9)) {
                              var10 = true;
                              break label119;
                           }
                        }
                     }
                  }
               } catch (Throwable var14) {
                  try {
                     var5.close();
                  } catch (Throwable var11) {
                     var14.addSuppressed(var11);
                  }

                  throw var14;
               }

               var5.close();
               return var1 != null && var1.getCurrentSize() <= 131072L && uX.pC(var1);
            }

            var5.close();
            return var10;
         } catch (IOException var15) {
         }
      } else if (var3 != null && var3.endsWith(".s7blk")) {
         return uX.pC(var1);
      }

      return var1 != null && var1.getCurrentSize() <= 131072L && uX.pC(var1);
   }

   @Override
   public IUnit prepare(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, Map var5) {
      return new gb(var1, var2, var3, var4, this.pdm);
   }
}
