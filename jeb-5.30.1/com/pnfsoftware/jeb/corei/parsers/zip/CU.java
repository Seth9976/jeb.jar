package com.pnfsoftware.jeb.corei.parsers.zip;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.IDataProvider;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IArchiveUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.impl.DataContainerUnit;
import com.pnfsoftware.jeb.core.units.impl.LazyDataContainerUnit;
import com.pnfsoftware.jeb.util.encoding.zip.fsr.ZipData;
import com.pnfsoftware.jeb.util.encoding.zip.fsr.ZipEntry;
import com.pnfsoftware.jeb.util.encoding.zip.fsr.ZipFailSafeReader;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.cvi;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;

@Ser
public class CU extends AbstractBinaryUnit implements IDataProvider, IArchiveUnit {
   private static final ILogger RF = GlobalLog.getLogger(com.pnfsoftware.jeb.corei.parsers.zip.CU.class);
   @SerId(1)
   boolean q;

   public CU(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5, String var6) {
      super("application/zip", var2, var6 != null ? var6 : "zip", var1, var3, var4, var5);
   }

   public CU(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      this(var1, var2, var3, var4, var5, null);
   }

   public void q(boolean var1) {
      this.q = var1;
   }

   public boolean q() {
      return this.q;
   }

   @Override
   public long getDataSize(String var1, int var2) {
      try {
         ZipFile var3 = new ZipFile(this.getInput().getChannel());

         label62: {
            long var8;
            try {
               Iterable var16 = var3.getEntries(var1);
               int var17 = 0;
               Iterator var18 = var16.iterator();

               while (true) {
                  if (!var18.hasNext()) {
                     break label62;
                  }

                  ZipArchiveEntry var7 = (ZipArchiveEntry)var18.next();
                  if (!var7.isDirectory()) {
                     if (var17 >= var2) {
                        var8 = var7.getSize();
                        break;
                     }

                     var17++;
                  }
               }
            } catch (Throwable var12) {
               try {
                  var3.close();
               } catch (Throwable var11) {
                  var12.addSuppressed(var11);
               }

               throw var12;
            }

            var3.close();
            return var8;
         }

         var3.close();
      } catch (IOException var15) {
         try (ZipFailSafeReader var4 = new ZipFailSafeReader(this.getInput().getChannel(), -1, false, true, false)) {
            ZipEntry var5 = var4.getEntry(var1);
            if (var5 != null) {
               return var5.getFilesize();
            }

            return -1L;
         } catch (IOException var14) {
         }
      }

      return -1L;
   }

   @Override
   public byte[] getDataBytes(String var1, int var2) {
      try {
         ZipFile var3 = new ZipFile(this.getInput().getChannel());

         byte[] var25;
         label112: {
            byte[] var9;
            label111: {
               Object var22;
               try {
                  Iterable var19 = var3.getEntries(var1);
                  int var20 = 0;

                  for (ZipArchiveEntry var23 : var19) {
                     if (!var23.isDirectory()) {
                        if (var20 >= var2) {
                           if (!var3.canReadEntryData(var23)) {
                              var25 = new byte[0];
                              break label112;
                           }

                           try (InputStream var24 = var3.getInputStream(var23)) {
                              var9 = IO.readInputStream(var24);
                              break label111;
                           }
                        }

                        var20++;
                     }
                  }

                  var22 = null;
               } catch (Throwable var17) {
                  try {
                     var3.close();
                  } catch (Throwable var12) {
                     var17.addSuppressed(var12);
                  }

                  throw var17;
               }

               var3.close();
               return (byte[])var22;
            }

            var3.close();
            return var9;
         }

         var3.close();
         return var25;
      } catch (IOException var18) {
         try {
            label81: {
               byte[] var8;
               try (ZipFailSafeReader var4 = new ZipFailSafeReader(this.getInput().getChannel(), -1, false, true, false)) {
                  ZipEntry var5 = var4.getEntry(var1);
                  if (var5 == null) {
                     break label81;
                  }

                  ZipData var6 = var4.readData(var5);
                  String var7 = Strings.ff(S.L("Recovered entry: %s"), var5.getFilenameUTF8());
                  if (var6.getException() != null) {
                     this.logError(true, S.L("%s (error: %s)"), var7, var6.getException().toString());
                  } else {
                     this.logWarn(true, var7);
                  }

                  var8 = var6.getUncompressedData();
               }

               return var8;
            }
         } catch (IOException var15) {
            RF.catchingSilent(var15);
            return null;
         }

         RF.catchingSilent(var18);
         return null;
      }
   }

   @Override
   public int getDataBytes(String var1, int var2, byte[] var3, int var4, int var5) {
      try {
         ZipFile var6 = new ZipFile(this.getInput().getChannel());

         byte var19;
         label72: {
            int var12;
            try {
               Iterable var7 = var6.getEntries(var1);
               int var8 = 0;
               Iterator var9 = var7.iterator();

               while (true) {
                  if (!var9.hasNext()) {
                     throw new IOException("Entry not found");
                  }

                  ZipArchiveEntry var10 = (ZipArchiveEntry)var9.next();
                  if (!var10.isDirectory()) {
                     if (var8 >= var2) {
                        if (!var6.canReadEntryData(var10)) {
                           var19 = 0;
                           break label72;
                        }

                        try (InputStream var11 = var6.getInputStream(var10)) {
                           var12 = var11.read(var3, var4, var5);
                           break;
                        }
                     }

                     var8++;
                  }
               }
            } catch (Throwable var17) {
               try {
                  var6.close();
               } catch (Throwable var14) {
                  var17.addSuppressed(var14);
               }

               throw var17;
            }

            var6.close();
            return var12;
         }

         var6.close();
         return var19;
      } catch (IOException var18) {
         return -1;
      }
   }

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   @Override
   public InputStream openDataStream(String var1, int var2) throws IOException {
      ZipArchiveInputStream var3 = new ZipArchiveInputStream(this.getInput().getStream());
      int var4 = 0;

      while (true) {
         ZipArchiveEntry var5;
         while (true) {
            try {
               var5 = var3.getNextZipEntry();
               if (var5 == null) {
                  throw new IOException("Entry not found");
               }

               if (Strings.equals(var5.getName(), var1) && !var5.isDirectory()) {
                  break;
               }
            } catch (IOException var8) {
               var3.close();
               throw var8;
            }
         }

         if (var4 >= var2) {
            try {
               if (!var3.canReadEntryData(var5)) {
                  throw new IOException("Cannot read entry");
               }

               return var3;
            } catch (IOException var7) {
               var3.close();
               throw var7;
            }
         }

         var4++;
      }
   }

   @Override
   protected boolean processInternal() {
      try {
         boolean var1 = !this.getPropertyManager().getBoolean("ProcessEntriesLazily");
         if (var1 || this.q) {
            var1 = true;
         }

         if (var1) {
            Object[] var10000 = new Object[0];
            DataContainerUnit.register(this, new com.pnfsoftware.jeb.corei.parsers.zip.CU.CU().q());
         } else {
            Object[] var3 = new Object[0];
            LazyDataContainerUnit.register(this, new com.pnfsoftware.jeb.corei.parsers.zip.CU.nI().q());
         }

         return true;
      } catch (IOException var2) {
         RF.catching(var2);
         return false;
      }
   }

   class CU extends com.pnfsoftware.jeb.corei.parsers.zip.CU.eo {
      public DataContainerUnit.Entry q(String var1) {
         return DataContainerUnit.Entry.createFolder(var1);
      }

      public DataContainerUnit.Entry q(String var1, int var2) throws IOException {
         byte[] var3;
         if (this.Dw == null) {
            var3 = new byte[0];
         } else {
            var3 = IO.readInputStream(this.Dw);
         }

         return DataContainerUnit.Entry.create(var1, var3);
      }

      public Map q(DataContainerUnit.Entry var1) {
         return var1.getChildren();
      }

      public boolean RF(DataContainerUnit.Entry var1) {
         return var1.isFolder();
      }
   }

   abstract class eo extends cvi {
      String q;
      long RF;
      ZipArchiveEntry xK;
      InputStream Dw;

      public Collection q() throws IOException {
         TreeMap var1 = new TreeMap();

         try {
            ZipFile var2 = new ZipFile(CU.this.getInput().getChannel());

            try {
               Enumeration var16 = var2.getEntries();

               while (var16.hasMoreElements()) {
                  try {
                     ZipArchiveEntry var17 = (ZipArchiveEntry)var16.nextElement();
                     String var19 = var17.getName();
                     long var18;
                     if (!var2.canReadEntryData(var17)) {
                        var18 = 0L;
                        CU.this.logError(
                           true,
                           S.L("The ZIP entry \"%s\" cannot be read; it is either encrypted or is using an unsupported compression scheme"),
                           Strings.truncateWithSuffix(var17.getName(), 30, "...")
                        );
                     } else {
                        var18 = var17.getSize();
                     }

                     List var20 = IO.parsePathElements(var19);
                     if (var20.isEmpty()) {
                        CU.this.logWarn(true, S.L("Illegal zip entry name: \"%s\""), var19);
                     } else {
                        this.q(var19, var18, var17, var2.getInputStream(var17));
                        this.q(var1, var20, var17.isDirectory());
                     }
                  } catch (IOException var13) {
                     com.pnfsoftware.jeb.corei.parsers.zip.CU.RF.catchingSilent(var13);
                     break;
                  }
               }
            } catch (Throwable var14) {
               try {
                  var2.close();
               } catch (Throwable var10) {
                  var14.addSuppressed(var10);
               }

               throw var14;
            }

            var2.close();
         } catch (IOException var15) {
            ZipArchiveInputStream var3 = new ZipArchiveInputStream(CU.this.getInput().getStream());

            try {
               while (true) {
                  try {
                     ZipArchiveEntry var4 = var3.getNextZipEntry();
                     if (var4 == null) {
                        break;
                     }

                     String var7 = var4.getName();
                     long var5;
                     if (!var3.canReadEntryData(var4)) {
                        var5 = 0L;
                        CU.this.logError(
                           true,
                           S.L("The ZIP entry \"%s\" cannot be read; it is either encrypted or is using an unsupported compression scheme"),
                           Strings.truncateWithSuffix(var4.getName(), 30, "...")
                        );
                     } else {
                        var5 = var4.getSize();
                     }

                     List var8 = IO.parsePathElements(var7);
                     if (var8.isEmpty()) {
                        CU.this.logWarn(true, S.L("Illegal zip entry name: \"%s\""), var7);
                     } else {
                        this.q(var7, var5, var4, var3.canReadEntryData(var4) ? var3 : null);
                        this.q(var1, var8, var4.isDirectory());
                     }
                  } catch (IOException var11) {
                     com.pnfsoftware.jeb.corei.parsers.zip.CU.RF.catchingSilent(var11);
                     break;
                  }
               }
            } catch (Throwable var12) {
               try {
                  var3.close();
               } catch (Throwable var9) {
                  var12.addSuppressed(var9);
               }

               throw var12;
            }

            var3.close();
         }

         return var1.values();
      }

      void q(String var1, long var2, ZipArchiveEntry var4, InputStream var5) {
         this.q = var1;
         this.RF = var2;
         this.xK = var4;
         this.Dw = var5;
      }
   }

   class nI extends com.pnfsoftware.jeb.corei.parsers.zip.CU.eo {
      public LazyDataContainerUnit.Entry q(String var1) {
         return LazyDataContainerUnit.Entry.createFolder(var1);
      }

      public LazyDataContainerUnit.Entry q(String var1, int var2) {
         return LazyDataContainerUnit.Entry.create(CU.this, this.q, this.RF, var1, var2);
      }

      public Map q(LazyDataContainerUnit.Entry var1) {
         return var1.getChildren();
      }

      public boolean RF(LazyDataContainerUnit.Entry var1) {
         return var1.isFolder();
      }
   }
}
