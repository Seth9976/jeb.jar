package com.pnfsoftware.jebglobal;

class IQ implements N {
   IQ(fK var1, String var2, int var3) {
      this.kS = var1;
      this.pC = var2;
      this.A = var3;
   }

   @Override
   public void pC(int param1, long param2, Jx param4, long param5) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.RuntimeException: parsing failure!
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:211)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:166)
      //
      // Bytecode:
      // 00: aload 0
      // 01: getfield com/pnfsoftware/jebglobal/IQ.kS Lcom/pnfsoftware/jebglobal/fK;
      // 04: dup
      // 05: astore 7
      // 07: monitorenter
      // 08: aload 0
      // 09: getfield com/pnfsoftware/jebglobal/IQ.kS Lcom/pnfsoftware/jebglobal/fK;
      // 0c: getfield com/pnfsoftware/jebglobal/fK.ys Ljava/util/Map;
      // 0f: aload 0
      // 10: getfield com/pnfsoftware/jebglobal/IQ.pC Ljava/lang/String;
      // 13: invokeinterface java/util/Map.get (Ljava/lang/Object;)Ljava/lang/Object; 2
      // 18: checkcast com/pnfsoftware/jebglobal/fK$Av
      // 1b: getfield com/pnfsoftware/jebglobal/fK$Av.pC Ljava/util/List;
      // 1e: invokeinterface java/util/List.iterator ()Ljava/util/Iterator; 1
      // 23: astore 8
      // 25: aload 8
      // 27: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 2c: ifeq 86
      // 2f: aload 8
      // 31: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 36: checkcast com/pnfsoftware/jebglobal/PZ
      // 39: astore 9
      // 3b: aload 0
      // 3c: getfield com/pnfsoftware/jebglobal/IQ.kS Lcom/pnfsoftware/jebglobal/fK;
      // 3f: getfield com/pnfsoftware/jebglobal/fK.UT Lcom/pnfsoftware/jebglobal/bA;
      // 42: invokevirtual com/pnfsoftware/jebglobal/bA.oT ()Lcom/pnfsoftware/jebglobal/y;
      // 45: aload 9
      // 47: lload 5
      // 49: invokevirtual com/pnfsoftware/jebglobal/y.pC (Lcom/pnfsoftware/jebglobal/PZ;J)Lcom/pnfsoftware/jebglobal/Jx;
      // 4c: astore 10
      // 4e: aload 10
      // 50: ifnonnull 6d
      // 53: getstatic com/pnfsoftware/jebglobal/fK.wS Lcom/pnfsoftware/jeb/util/logging/ILogger;
      // 56: ldc "Can not resolve breakpoint for %s"
      // 58: invokestatic com/pnfsoftware/jeb/client/S.L (Ljava/lang/String;)Ljava/lang/String;
      // 5b: bipush 1
      // 5c: anewarray 15
      // 5f: dup
      // 60: bipush 0
      // 61: aload 9
      // 63: aastore
      // 64: invokeinterface com/pnfsoftware/jeb/util/logging/ILogger.error (Ljava/lang/String;[Ljava/lang/Object;)V 3
      // 69: aload 7
      // 6b: monitorexit
      // 6c: return
      // 6d: goto 75
      // 70: pop
      // 71: aload 7
      // 73: monitorexit
      // 74: return
      // 75: aload 0
      // 76: getfield com/pnfsoftware/jebglobal/IQ.kS Lcom/pnfsoftware/jebglobal/fK;
      // 79: aload 10
      // 7b: aload 0
      // 7c: getfield com/pnfsoftware/jebglobal/IQ.A I
      // 7f: invokevirtual com/pnfsoftware/jebglobal/fK.pC (Lcom/pnfsoftware/jebglobal/Jx;I)Z
      // 82: pop
      // 83: goto 25
      // 86: aload 7
      // 88: monitorexit
      // 89: goto 94
      // 8c: astore 11
      // 8e: aload 7
      // 90: monitorexit
      // 91: aload 11
      // 93: athrow
      // 94: return
   }
}
