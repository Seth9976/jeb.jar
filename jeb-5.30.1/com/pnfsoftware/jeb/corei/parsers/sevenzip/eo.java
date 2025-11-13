package com.pnfsoftware.jeb.corei.parsers.sevenzip;

import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.IDataProvider;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IArchiveUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.impl.LazyDataContainerUnit;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jebglobal.cvj;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.TreeMap;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

@Ser
public class eo extends AbstractBinaryUnit implements IDataProvider, IArchiveUnit {
   private static final ILogger q = GlobalLog.getLogger(com.pnfsoftware.jeb.corei.parsers.sevenzip.eo.class);

   public eo(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super("application/x-7z-compressed", var2, "sevenzip", var1, var3, var4, var5);
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
      // 00: new org/apache/commons/compress/archivers/sevenz/SevenZFile
      // 03: dup
      // 04: aload 0
      // 05: invokevirtual com/pnfsoftware/jeb/corei/parsers/sevenzip/eo.getInput ()Lcom/pnfsoftware/jeb/core/input/IInput;
      // 08: invokeinterface com/pnfsoftware/jeb/core/input/IInput.getChannel ()Ljava/nio/channels/SeekableByteChannel; 1
      // 0d: invokespecial org/apache/commons/compress/archivers/sevenz/SevenZFile.<init> (Ljava/nio/channels/SeekableByteChannel;)V
      // 10: astore 3
      // 11: bipush 0
      // 12: istore 4
      // 14: aload 3
      // 15: invokevirtual org/apache/commons/compress/archivers/sevenz/SevenZFile.getNextEntry ()Lorg/apache/commons/compress/archivers/sevenz/SevenZArchiveEntry;
      // 18: astore 5
      // 1a: aload 5
      // 1c: ifnonnull 29
      // 1f: new java/io/IOException
      // 22: dup
      // 23: ldc "Entry not found"
      // 25: invokespecial java/io/IOException.<init> (Ljava/lang/String;)V
      // 28: athrow
      // 29: aload 5
      // 2b: invokevirtual org/apache/commons/compress/archivers/sevenz/SevenZArchiveEntry.getName ()Ljava/lang/String;
      // 2e: aload 1
      // 2f: invokestatic com/pnfsoftware/jeb/util/format/Strings.equals (Ljava/lang/String;Ljava/lang/String;)Z
      // 32: ifeq 3d
      // 35: aload 5
      // 37: invokevirtual org/apache/commons/compress/archivers/sevenz/SevenZArchiveEntry.isDirectory ()Z
      // 3a: ifeq 40
      // 3d: goto 14
      // 40: iload 4
      // 42: iload 2
      // 43: if_icmpge 4c
      // 46: iinc 4 1
      // 49: goto 14
      // 4c: new com/pnfsoftware/jeb/corei/parsers/sevenzip/CU
      // 4f: dup
      // 50: aload 0
      // 51: aload 3
      // 52: invokespecial com/pnfsoftware/jeb/corei/parsers/sevenzip/CU.<init> (Lcom/pnfsoftware/jeb/corei/parsers/sevenzip/eo;Lorg/apache/commons/compress/archivers/sevenz/SevenZFile;)V
      // 55: astore 6
      // 57: aload 6
      // 59: invokestatic com/pnfsoftware/jeb/util/io/IO.readInputStream (Ljava/io/InputStream;)[B
      // 5c: astore 7
      // 5e: aload 6
      // 60: invokevirtual java/io/InputStream.close ()V
      // 63: aload 3
      // 64: invokevirtual org/apache/commons/compress/archivers/sevenz/SevenZFile.close ()V
      // 67: aload 7
      // 69: areturn
      // 6a: astore 7
      // 6c: aload 6
      // 6e: invokevirtual java/io/InputStream.close ()V
      // 71: goto 7d
      // 74: astore 8
      // 76: aload 7
      // 78: aload 8
      // 7a: invokevirtual java/lang/Throwable.addSuppressed (Ljava/lang/Throwable;)V
      // 7d: aload 7
      // 7f: athrow
      // 80: astore 6
      // 82: getstatic com/pnfsoftware/jeb/corei/parsers/sevenzip/eo.q Lcom/pnfsoftware/jeb/util/logging/ILogger;
      // 85: aload 6
      // 87: invokeinterface com/pnfsoftware/jeb/util/logging/ILogger.catchingSilent (Ljava/lang/Throwable;)V 2
      // 8c: aconst_null
      // 8d: astore 7
      // 8f: aload 3
      // 90: invokevirtual org/apache/commons/compress/archivers/sevenz/SevenZFile.close ()V
      // 93: aload 7
      // 95: areturn
      // 96: astore 4
      // 98: aload 3
      // 99: invokevirtual org/apache/commons/compress/archivers/sevenz/SevenZFile.close ()V
      // 9c: goto a8
      // 9f: astore 5
      // a1: aload 4
      // a3: aload 5
      // a5: invokevirtual java/lang/Throwable.addSuppressed (Ljava/lang/Throwable;)V
      // a8: aload 4
      // aa: athrow
      // ab: astore 3
      // ac: getstatic com/pnfsoftware/jeb/corei/parsers/sevenzip/eo.q Lcom/pnfsoftware/jeb/util/logging/ILogger;
      // af: aload 3
      // b0: invokeinterface com/pnfsoftware/jeb/util/logging/ILogger.catchingSilent (Ljava/lang/Throwable;)V 2
      // b5: aconst_null
      // b6: areturn
   }

   @Override
   public long getDataSize(String var1, int var2) {
      return 0L;
   }

   @Override
   protected boolean processInternal() {
      try {
         LazyDataContainerUnit.register(this, new com.pnfsoftware.jeb.corei.parsers.sevenzip.eo.eo().q());
         return true;
      } catch (IOException var2) {
         this.logException(var2);
         return false;
      }
   }

   class eo extends cvj {
      public Collection q() throws IOException {
         TreeMap var1 = new TreeMap();
         SevenZFile var2 = new SevenZFile(eo.this.getInput().getChannel());

         try {
            while (true) {
               try {
                  SevenZArchiveEntry var3 = var2.getNextEntry();
                  if (var3 == null) {
                     break;
                  }

                  String var4 = var3.getName();
                  long var5 = var3.getSize();
                  String[] var7 = var4.split("/");
                  this.q(var4, var5);
                  this.q(var1, Arrays.asList(var7), var3.isDirectory());
               } catch (IOException var9) {
                  com.pnfsoftware.jeb.corei.parsers.sevenzip.eo.q.catching(var9);
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
