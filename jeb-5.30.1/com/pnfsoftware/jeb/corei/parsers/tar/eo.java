package com.pnfsoftware.jeb.corei.parsers.tar;

import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.IDataProvider;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IArchiveUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.impl.LazyDataContainerUnit;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jebglobal.cvj;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.TreeMap;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

@Ser
public class eo extends AbstractBinaryUnit implements IDataProvider, IArchiveUnit {
   private static final ILogger q = GlobalLog.getLogger(com.pnfsoftware.jeb.corei.parsers.tar.eo.class);

   public eo(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super("application/x-tar", var2, "tar", var1, var3, var4, var5);
   }

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   @Override
   public InputStream openDataStream(String var1, int var2) throws IOException {
      TarArchiveInputStream var3 = new TarArchiveInputStream(this.getInput().getStream());
      int var4 = 0;

      while (true) {
         TarArchiveEntry var5;
         while (true) {
            try {
               var5 = var3.getNextTarEntry();
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
   public byte[] getDataBytes(String param1, int param2) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.NullPointerException: Cannot invoke "org.jetbrains.java.decompiler.code.cfg.ExceptionRangeCFG.isCircular()" because "range" is null
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.graphToStatement(DomHelper.java:84)
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:203)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:166)
      //
      // Bytecode:
      // 00: new org/apache/commons/compress/archivers/tar/TarArchiveInputStream
      // 03: dup
      // 04: aload 0
      // 05: invokevirtual com/pnfsoftware/jeb/corei/parsers/tar/eo.getInput ()Lcom/pnfsoftware/jeb/core/input/IInput;
      // 08: invokeinterface com/pnfsoftware/jeb/core/input/IInput.getStream ()Ljava/io/InputStream; 1
      // 0d: invokespecial org/apache/commons/compress/archivers/tar/TarArchiveInputStream.<init> (Ljava/io/InputStream;)V
      // 10: astore 3
      // 11: bipush 0
      // 12: istore 4
      // 14: aload 3
      // 15: invokevirtual org/apache/commons/compress/archivers/tar/TarArchiveInputStream.getNextTarEntry ()Lorg/apache/commons/compress/archivers/tar/TarArchiveEntry;
      // 18: astore 5
      // 1a: aload 5
      // 1c: ifnonnull 29
      // 1f: new java/io/IOException
      // 22: dup
      // 23: ldc "Entry not found"
      // 25: invokespecial java/io/IOException.<init> (Ljava/lang/String;)V
      // 28: athrow
      // 29: aload 5
      // 2b: invokevirtual org/apache/commons/compress/archivers/tar/TarArchiveEntry.getName ()Ljava/lang/String;
      // 2e: aload 1
      // 2f: invokestatic com/pnfsoftware/jeb/util/format/Strings.equals (Ljava/lang/String;Ljava/lang/String;)Z
      // 32: ifeq 3d
      // 35: aload 5
      // 37: invokevirtual org/apache/commons/compress/archivers/tar/TarArchiveEntry.isDirectory ()Z
      // 3a: ifeq 40
      // 3d: goto 14
      // 40: iload 4
      // 42: iload 2
      // 43: if_icmpge 4c
      // 46: iinc 4 1
      // 49: goto 14
      // 4c: aload 3
      // 4d: aload 5
      // 4f: invokevirtual org/apache/commons/compress/archivers/tar/TarArchiveInputStream.canReadEntryData (Lorg/apache/commons/compress/archivers/ArchiveEntry;)Z
      // 52: ifne 5f
      // 55: new java/io/IOException
      // 58: dup
      // 59: ldc "Cannot read entry"
      // 5b: invokespecial java/io/IOException.<init> (Ljava/lang/String;)V
      // 5e: athrow
      // 5f: aload 5
      // 61: invokevirtual org/apache/commons/compress/archivers/tar/TarArchiveEntry.getSize ()J
      // 64: ldc2_w 2147483647
      // 67: lcmp
      // 68: ifle 75
      // 6b: new java/io/IOException
      // 6e: dup
      // 6f: ldc "Entry is too large for a full read"
      // 71: invokespecial java/io/IOException.<init> (Ljava/lang/String;)V
      // 74: athrow
      // 75: aload 5
      // 77: invokevirtual org/apache/commons/compress/archivers/tar/TarArchiveEntry.getSize ()J
      // 7a: l2i
      // 7b: newarray 8
      // 7d: astore 6
      // 7f: aload 3
      // 80: aload 6
      // 82: invokevirtual org/apache/commons/compress/archivers/tar/TarArchiveInputStream.read ([B)I
      // 85: istore 7
      // 87: iload 7
      // 89: i2l
      // 8a: aload 5
      // 8c: invokevirtual org/apache/commons/compress/archivers/tar/TarArchiveEntry.getSize ()J
      // 8f: lcmp
      // 90: ifeq 9d
      // 93: new java/io/IOException
      // 96: dup
      // 97: ldc "Illegal size"
      // 99: invokespecial java/io/IOException.<init> (Ljava/lang/String;)V
      // 9c: athrow
      // 9d: aload 6
      // 9f: astore 8
      // a1: aload 3
      // a2: invokevirtual org/apache/commons/compress/archivers/tar/TarArchiveInputStream.close ()V
      // a5: aload 8
      // a7: areturn
      // a8: astore 6
      // aa: getstatic com/pnfsoftware/jeb/corei/parsers/tar/eo.q Lcom/pnfsoftware/jeb/util/logging/ILogger;
      // ad: aload 6
      // af: invokeinterface com/pnfsoftware/jeb/util/logging/ILogger.catchingSilent (Ljava/lang/Throwable;)V 2
      // b4: aconst_null
      // b5: astore 7
      // b7: aload 3
      // b8: invokevirtual org/apache/commons/compress/archivers/tar/TarArchiveInputStream.close ()V
      // bb: aload 7
      // bd: areturn
      // be: astore 4
      // c0: aload 3
      // c1: invokevirtual org/apache/commons/compress/archivers/tar/TarArchiveInputStream.close ()V
      // c4: goto d0
      // c7: astore 5
      // c9: aload 4
      // cb: aload 5
      // cd: invokevirtual java/lang/Throwable.addSuppressed (Ljava/lang/Throwable;)V
      // d0: aload 4
      // d2: athrow
      // d3: astore 3
      // d4: getstatic com/pnfsoftware/jeb/corei/parsers/tar/eo.q Lcom/pnfsoftware/jeb/util/logging/ILogger;
      // d7: aload 3
      // d8: invokeinterface com/pnfsoftware/jeb/util/logging/ILogger.catchingSilent (Ljava/lang/Throwable;)V 2
      // dd: aconst_null
      // de: areturn
   }

   @Override
   public int getDataBytes(String var1, int var2, byte[] var3, int var4, int var5) {
      try {
         TarArchiveInputStream var6 = new TarArchiveInputStream(this.getInput().getStream());

         int var9;
         try {
            int var7 = 0;

            while (true) {
               TarArchiveEntry var8 = var6.getNextTarEntry();
               if (var8 == null) {
                  throw new IOException("Entry not found");
               }

               if (Strings.equals(var8.getName(), var1) && !var8.isDirectory()) {
                  if (var7 >= var2) {
                     if (!var6.canReadEntryData(var8)) {
                        throw new IOException("Cannot read entry");
                     }

                     var9 = var6.read(var3, var4, var5);
                     break;
                  }

                  var7++;
               }
            }
         } catch (Throwable var11) {
            try {
               var6.close();
            } catch (Throwable var10) {
               var11.addSuppressed(var10);
            }

            throw var11;
         }

         var6.close();
         return var9;
      } catch (IOException var12) {
         q.catchingSilent(var12);
         return -1;
      }
   }

   @Override
   public long getDataSize(String var1, int var2) {
      return 0L;
   }

   @Override
   protected boolean processInternal() {
      try {
         LazyDataContainerUnit.register(this, new com.pnfsoftware.jeb.corei.parsers.tar.eo.eo().q());
         return true;
      } catch (IOException var2) {
         q.catching(var2);
         return false;
      }
   }

   class eo extends cvj {
      public Collection q() throws IOException {
         TreeMap var1 = new TreeMap();
         TarArchiveInputStream var2 = new TarArchiveInputStream(eo.this.getInput().getStream());

         try {
            while (true) {
               try {
                  TarArchiveEntry var3 = var2.getNextTarEntry();
                  if (var3 == null) {
                     break;
                  }

                  String var4 = var3.getName();
                  long var11 = var3.getSize();
                  String[] var7 = var4.split("/");
                  this.q(var4, var11);
                  this.q(var1, Arrays.asList(var7), var3.isDirectory());
               } catch (IOException var9) {
                  String var5 = var9.getMessage();
                  if (var5 != null && var5.equals("Truncated TAR archive") && eo.this.getInput().getCurrentSize() > 4294967295L) {
                     com.pnfsoftware.jeb.corei.parsers.tar.eo.q.catchingSilent(var9);
                  } else {
                     com.pnfsoftware.jeb.corei.parsers.tar.eo.q.catching(var9);
                  }
                  break;
               }
            }
         } catch (Throwable var10) {
            try {
               var2.close();
            } catch (Throwable var8) {
               var10.addSuppressed(var8);
            }

            throw var10;
         }

         var2.close();
         return var1.values();
      }

      @Override
      public IDataProvider RF() {
         return eo.this;
      }
   }
}
