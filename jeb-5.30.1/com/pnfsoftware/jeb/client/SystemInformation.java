package com.pnfsoftware.jeb.client;

import com.pnfsoftware.jeb.util.base.OSType;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.cvm;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;

public class SystemInformation {
   private static final ILogger logger = GlobalLog.getLogger(SystemInformation.class);
   public static final String javavendor = System.getProperty(cvm.q(new byte[]{25, 11, 23, 23, 79, 88, 19, 11, 10, 11, 29}, 1, 115), "");
   public static final String javaversion = System.getProperty(cvm.q(new byte[]{41, 14, 6, 24, 92, 31, 2, 26, 7, 73, 71, 13}, 2, 224), "");
   public static final String osname = System.getProperty(cvm.q(new byte[]{-60, 28, 93, 64, 15, 12, 8}, 1, 171), "");
   public static final String osarch = System.getProperty(cvm.q(new byte[]{38, 28, 93, 79, 19, 17, 11}, 1, 73), "");
   public static final String osversion = System.getProperty(cvm.q(new byte[]{44, 28, 94, 15, 23, 27, 20, 1, 27, 78}, 2, 249), "");
   public static final String username = System.getProperty(cvm.q(new byte[]{-48, 6, 22, 23, 92, 64, 15, 12, 8}, 1, 165), "");
   public static final String compname;
   public static final String localeinfo;
   public static final String osfullname;

   public static String getCurrentDirectory() {
      return System.getProperty(cvm.q(new byte[]{54, 28, 21, 11, 92, 13, 14, 26}, 2, 162));
   }

   public static String setCurrentDirectory(String var0) {
      String var1 = getCurrentDirectory();
      logger.debug(S.L("Setting current directory: %s"), var0);
      System.setProperty(cvm.q(new byte[]{-5, 6, 22, 23, 92, 74, 13, 27}, 1, 142), var0);
      return var1;
   }

   public static String determineLinuxOSFullname(String param0) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.RuntimeException: parsing failure!
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:211)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:166)
      //
      // Bytecode:
      // 00: new java/io/File
      // 03: dup
      // 04: aload 0
      // 05: invokespecial java/io/File.<init> (Ljava/lang/String;)V
      // 08: astore 1
      // 09: aload 1
      // 0a: invokevirtual java/io/File.isFile ()Z
      // 0d: ifeq a6
      // 10: aload 1
      // 11: invokevirtual java/io/File.canRead ()Z
      // 14: ifeq a6
      // 17: aload 1
      // 18: invokestatic com/pnfsoftware/jeb/util/io/IO.readLines (Ljava/io/File;)Ljava/util/List;
      // 1b: invokeinterface java/util/List.iterator ()Ljava/util/Iterator; 1
      // 20: astore 2
      // 21: aload 2
      // 22: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 27: ifeq a6
      // 2a: aload 2
      // 2b: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 30: checkcast java/lang/String
      // 33: astore 3
      // 34: aload 3
      // 35: invokevirtual java/lang/String.trim ()Ljava/lang/String;
      // 38: astore 3
      // 39: aload 3
      // 3a: ldc "PRETTY_NAME"
      // 3c: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 3f: ifeq a3
      // 42: aload 3
      // 43: ldc "="
      // 45: invokevirtual java/lang/String.split (Ljava/lang/String;)[Ljava/lang/String;
      // 48: astore 4
      // 4a: aload 4
      // 4c: arraylength
      // 4d: bipush 2
      // 4e: if_icmpne a3
      // 51: aload 4
      // 53: bipush 0
      // 54: aaload
      // 55: invokevirtual java/lang/String.trim ()Ljava/lang/String;
      // 58: ldc "PRETTY_NAME"
      // 5a: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 5d: ifeq a3
      // 60: aload 4
      // 62: bipush 1
      // 63: aaload
      // 64: invokevirtual java/lang/String.trim ()Ljava/lang/String;
      // 67: astore 5
      // 69: aload 5
      // 6b: invokevirtual java/lang/String.length ()I
      // 6e: bipush 2
      // 6f: if_icmplt a0
      // 72: aload 5
      // 74: bipush 0
      // 75: invokevirtual java/lang/String.charAt (I)C
      // 78: bipush 34
      // 7a: if_icmpne a0
      // 7d: aload 5
      // 7f: aload 5
      // 81: invokevirtual java/lang/String.length ()I
      // 84: bipush 1
      // 85: isub
      // 86: invokevirtual java/lang/String.charAt (I)C
      // 89: bipush 34
      // 8b: if_icmpne a0
      // 8e: aload 5
      // 90: bipush 1
      // 91: aload 5
      // 93: invokevirtual java/lang/String.length ()I
      // 96: bipush 1
      // 97: isub
      // 98: invokevirtual java/lang/String.substring (II)Ljava/lang/String;
      // 9b: invokevirtual java/lang/String.trim ()Ljava/lang/String;
      // 9e: astore 5
      // a0: aload 5
      // a2: areturn
      // a3: goto 21
      // a6: goto aa
      // a9: pop
      // aa: ldc ""
      // ac: areturn
   }

   static {
      String var0 = "";

      try {
         var0 = InetAddress.getLocalHost().getHostName();
      } catch (UnknownHostException var2) {
      }

      compname = var0;
      Locale var1 = Locale.getDefault();
      localeinfo = var1.toString();
      if (OSType.determine().isLinux()) {
         osfullname = determineLinuxOSFullname("/etc/os-release");
      } else {
         osfullname = "";
      }
   }
}
