package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.ToDoException;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.util.ArrayList;
import java.util.HashMap;

public class bfp {
   private static final ILogger RF = GlobalLog.getLogger(bfp.class);
   bfr q;

   bfp(bfr var1) {
      Assert.a(var1.io, "Limited to AOT snapshots");
      this.q = var1;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   bfp.Bu q(int var1, boolean var2, boolean var3) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.RuntimeException: invalid constant type: Lcom/pnfsoftware/jebglobal/bfo; with value 13
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ConstExprent.toJava(ConstExprent.java:356)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SwitchStatement.toJava(SwitchStatement.java:171)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:258)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:251)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.RootStatement.toJava(RootStatement.java:36)
      //   at org.jetbrains.java.decompiler.main.ClassWriter.writeMethod(ClassWriter.java:1326)
      //
      // Bytecode:
      // 000: iload 1
      // 001: invokestatic com/pnfsoftware/jebglobal/bfo.q (I)Lcom/pnfsoftware/jebglobal/bfo;
      // 004: astore 4
      // 006: iload 1
      // 007: getstatic com/pnfsoftware/jebglobal/bfo.zm Lcom/pnfsoftware/jebglobal/bfo;
      // 00a: invokevirtual com/pnfsoftware/jebglobal/bfo.q ()I
      // 00d: if_icmpge 01a
      // 010: iload 1
      // 011: getstatic com/pnfsoftware/jebglobal/bfo.Rv Lcom/pnfsoftware/jebglobal/bfo;
      // 014: invokevirtual com/pnfsoftware/jebglobal/bfo.q ()I
      // 017: if_icmpne 025
      // 01a: new com/pnfsoftware/jebglobal/bfp$bK
      // 01d: dup
      // 01e: aload 0
      // 01f: iload 1
      // 020: iload 2
      // 021: invokespecial com/pnfsoftware/jebglobal/bfp$bK.<init> (Lcom/pnfsoftware/jebglobal/bfp;IZ)V
      // 024: areturn
      // 025: aload 0
      // 026: getfield com/pnfsoftware/jebglobal/bfp.q Lcom/pnfsoftware/jebglobal/bfr;
      // 029: iload 1
      // 02a: invokevirtual com/pnfsoftware/jebglobal/bfr.za (I)Z
      // 02d: ifeq 046
      // 030: iload 2
      // 031: ifne 038
      // 034: bipush 1
      // 035: goto 039
      // 038: bipush 0
      // 039: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 03c: new com/pnfsoftware/jebglobal/bfp$qx
      // 03f: dup
      // 040: aload 0
      // 041: iload 1
      // 042: invokespecial com/pnfsoftware/jebglobal/bfp$qx.<init> (Lcom/pnfsoftware/jebglobal/bfp;I)V
      // 045: areturn
      // 046: aload 0
      // 047: getfield com/pnfsoftware/jebglobal/bfp.q Lcom/pnfsoftware/jebglobal/bfr;
      // 04a: getfield com/pnfsoftware/jebglobal/bfr.xW Z
      // 04d: ifne 0a4
      // 050: aload 0
      // 051: getfield com/pnfsoftware/jebglobal/bfp.q Lcom/pnfsoftware/jebglobal/bfr;
      // 054: getfield com/pnfsoftware/jebglobal/bfr.KT Z
      // 057: ifeq 0a4
      // 05a: getstatic com/pnfsoftware/jebglobal/bfq.q [I
      // 05d: aload 4
      // 05f: invokevirtual com/pnfsoftware/jebglobal/bfo.ordinal ()I
      // 062: iaload
      // 063: tableswitch 65 1 6 37 37 37 49 49 49
      // 088: new com/pnfsoftware/jebglobal/bfp$eM
      // 08b: dup
      // 08c: aload 0
      // 08d: iload 2
      // 08e: iload 3
      // 08f: iload 1
      // 090: invokespecial com/pnfsoftware/jebglobal/bfp$eM.<init> (Lcom/pnfsoftware/jebglobal/bfp;ZZI)V
      // 093: areturn
      // 094: iload 3
      // 095: ifeq 0a4
      // 098: new com/pnfsoftware/jebglobal/bfp$eM
      // 09b: dup
      // 09c: aload 0
      // 09d: iload 2
      // 09e: iload 3
      // 09f: iload 1
      // 0a0: invokespecial com/pnfsoftware/jebglobal/bfp$eM.<init> (Lcom/pnfsoftware/jebglobal/bfp;ZZI)V
      // 0a3: areturn
      // 0a4: getstatic com/pnfsoftware/jebglobal/bfq.q [I
      // 0a7: aload 4
      // 0a9: invokevirtual com/pnfsoftware/jebglobal/bfo.ordinal ()I
      // 0ac: iaload
      // 0ad: tableswitch 923 1 47 453 474 495 923 923 894 203 224 233 244 265 286 307 328 349 370 391 411 432 516 537 551 565 586 600 614 635 656 670 684 698 709 720 741 752 762 772 782 803 817 831 845 853 864 872 883 883
      // 178: iload 2
      // 179: ifne 180
      // 17c: bipush 1
      // 17d: goto 181
      // 180: bipush 0
      // 181: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 184: new com/pnfsoftware/jebglobal/bfp$CU
      // 187: dup
      // 188: aload 0
      // 189: invokespecial com/pnfsoftware/jebglobal/bfp$CU.<init> (Lcom/pnfsoftware/jebglobal/bfp;)V
      // 18c: areturn
      // 18d: new com/pnfsoftware/jebglobal/bfp$ct
      // 190: dup
      // 191: aload 0
      // 192: invokespecial com/pnfsoftware/jebglobal/bfp$ct.<init> (Lcom/pnfsoftware/jebglobal/bfp;)V
      // 195: areturn
      // 196: new com/pnfsoftware/jebglobal/bfp$ME
      // 199: dup
      // 19a: aload 0
      // 19b: iload 2
      // 19c: iload 3
      // 19d: invokespecial com/pnfsoftware/jebglobal/bfp$ME.<init> (Lcom/pnfsoftware/jebglobal/bfp;ZZ)V
      // 1a0: areturn
      // 1a1: iload 2
      // 1a2: ifne 1a9
      // 1a5: bipush 1
      // 1a6: goto 1aa
      // 1a9: bipush 0
      // 1aa: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 1ad: new com/pnfsoftware/jebglobal/bfp$zJ
      // 1b0: dup
      // 1b1: aload 0
      // 1b2: invokespecial com/pnfsoftware/jebglobal/bfp$zJ.<init> (Lcom/pnfsoftware/jebglobal/bfp;)V
      // 1b5: areturn
      // 1b6: iload 2
      // 1b7: ifne 1be
      // 1ba: bipush 1
      // 1bb: goto 1bf
      // 1be: bipush 0
      // 1bf: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 1c2: new com/pnfsoftware/jebglobal/bfp$vb
      // 1c5: dup
      // 1c6: aload 0
      // 1c7: invokespecial com/pnfsoftware/jebglobal/bfp$vb.<init> (Lcom/pnfsoftware/jebglobal/bfp;)V
      // 1ca: areturn
      // 1cb: iload 2
      // 1cc: ifne 1d3
      // 1cf: bipush 1
      // 1d0: goto 1d4
      // 1d3: bipush 0
      // 1d4: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 1d7: new com/pnfsoftware/jebglobal/bfp$nI
      // 1da: dup
      // 1db: aload 0
      // 1dc: invokespecial com/pnfsoftware/jebglobal/bfp$nI.<init> (Lcom/pnfsoftware/jebglobal/bfp;)V
      // 1df: areturn
      // 1e0: iload 2
      // 1e1: ifne 1e8
      // 1e4: bipush 1
      // 1e5: goto 1e9
      // 1e8: bipush 0
      // 1e9: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 1ec: new com/pnfsoftware/jebglobal/bfp$vn
      // 1ef: dup
      // 1f0: aload 0
      // 1f1: invokespecial com/pnfsoftware/jebglobal/bfp$vn.<init> (Lcom/pnfsoftware/jebglobal/bfp;)V
      // 1f4: areturn
      // 1f5: iload 2
      // 1f6: ifne 1fd
      // 1f9: bipush 1
      // 1fa: goto 1fe
      // 1fd: bipush 0
      // 1fe: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 201: new com/pnfsoftware/jebglobal/bfp$PY
      // 204: dup
      // 205: aload 0
      // 206: invokespecial com/pnfsoftware/jebglobal/bfp$PY.<init> (Lcom/pnfsoftware/jebglobal/bfp;)V
      // 209: areturn
      // 20a: iload 2
      // 20b: ifne 212
      // 20e: bipush 1
      // 20f: goto 213
      // 212: bipush 0
      // 213: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 216: new com/pnfsoftware/jebglobal/bfp$HA
      // 219: dup
      // 21a: aload 0
      // 21b: invokespecial com/pnfsoftware/jebglobal/bfp$HA.<init> (Lcom/pnfsoftware/jebglobal/bfp;)V
      // 21e: areturn
      // 21f: iload 2
      // 220: ifne 227
      // 223: bipush 1
      // 224: goto 228
      // 227: bipush 0
      // 228: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 22b: new com/pnfsoftware/jebglobal/bfp$tl
      // 22e: dup
      // 22f: aload 0
      // 230: invokespecial com/pnfsoftware/jebglobal/bfp$tl.<init> (Lcom/pnfsoftware/jebglobal/bfp;)V
      // 233: areturn
      // 234: iload 2
      // 235: ifne 23c
      // 238: bipush 1
      // 239: goto 23d
      // 23c: bipush 0
      // 23d: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 240: new com/pnfsoftware/jeb/core/exceptions/ToDoException
      // 243: dup
      // 244: invokespecial com/pnfsoftware/jeb/core/exceptions/ToDoException.<init> ()V
      // 247: athrow
      // 248: iload 2
      // 249: ifne 250
      // 24c: bipush 1
      // 24d: goto 251
      // 250: bipush 0
      // 251: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 254: new com/pnfsoftware/jebglobal/bfp$Nt
      // 257: dup
      // 258: aload 0
      // 259: invokespecial com/pnfsoftware/jebglobal/bfp$Nt.<init> (Lcom/pnfsoftware/jebglobal/bfp;)V
      // 25c: areturn
      // 25d: iload 2
      // 25e: ifne 265
      // 261: bipush 1
      // 262: goto 266
      // 265: bipush 0
      // 266: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 269: new com/pnfsoftware/jebglobal/bfp$vX
      // 26c: dup
      // 26d: aload 0
      // 26e: invokespecial com/pnfsoftware/jebglobal/bfp$vX.<init> (Lcom/pnfsoftware/jebglobal/bfp;)V
      // 271: areturn
      // 272: iload 2
      // 273: ifne 27a
      // 276: bipush 1
      // 277: goto 27b
      // 27a: bipush 0
      // 27b: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 27e: new com/pnfsoftware/jebglobal/bfp$KZ
      // 281: dup
      // 282: aload 0
      // 283: invokespecial com/pnfsoftware/jebglobal/bfp$KZ.<init> (Lcom/pnfsoftware/jebglobal/bfp;)V
      // 286: areturn
      // 287: iload 2
      // 288: ifne 28f
      // 28b: bipush 1
      // 28c: goto 290
      // 28f: bipush 0
      // 290: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 293: new com/pnfsoftware/jebglobal/bfp$iZ
      // 296: dup
      // 297: aload 0
      // 298: invokespecial com/pnfsoftware/jebglobal/bfp$iZ.<init> (Lcom/pnfsoftware/jebglobal/bfp;)V
      // 29b: areturn
      // 29c: iload 2
      // 29d: ifne 2a4
      // 2a0: bipush 1
      // 2a1: goto 2a5
      // 2a4: bipush 0
      // 2a5: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 2a8: new com/pnfsoftware/jebglobal/bfp$tw
      // 2ab: dup
      // 2ac: aload 0
      // 2ad: invokespecial com/pnfsoftware/jebglobal/bfp$tw.<init> (Lcom/pnfsoftware/jebglobal/bfp;)V
      // 2b0: areturn
      // 2b1: iload 2
      // 2b2: ifne 2b9
      // 2b5: bipush 1
      // 2b6: goto 2ba
      // 2b9: bipush 0
      // 2ba: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 2bd: new com/pnfsoftware/jebglobal/bfp$qV
      // 2c0: dup
      // 2c1: aload 0
      // 2c2: invokespecial com/pnfsoftware/jebglobal/bfp$qV.<init> (Lcom/pnfsoftware/jebglobal/bfp;)V
      // 2c5: areturn
      // 2c6: iload 2
      // 2c7: ifne 2ce
      // 2ca: bipush 1
      // 2cb: goto 2cf
      // 2ce: bipush 0
      // 2cf: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 2d2: aconst_null
      // 2d3: areturn
      // 2d4: iload 2
      // 2d5: ifne 2dc
      // 2d8: bipush 1
      // 2d9: goto 2dd
      // 2dc: bipush 0
      // 2dd: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 2e0: aconst_null
      // 2e1: areturn
      // 2e2: iload 2
      // 2e3: ifne 2ea
      // 2e6: bipush 1
      // 2e7: goto 2eb
      // 2ea: bipush 0
      // 2eb: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 2ee: new com/pnfsoftware/jebglobal/bfp$FL
      // 2f1: dup
      // 2f2: aload 0
      // 2f3: invokespecial com/pnfsoftware/jebglobal/bfp$FL.<init> (Lcom/pnfsoftware/jebglobal/bfp;)V
      // 2f6: areturn
      // 2f7: iload 2
      // 2f8: ifne 2ff
      // 2fb: bipush 1
      // 2fc: goto 300
      // 2ff: bipush 0
      // 300: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 303: aconst_null
      // 304: areturn
      // 305: iload 2
      // 306: ifne 30d
      // 309: bipush 1
      // 30a: goto 30e
      // 30d: bipush 0
      // 30e: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 311: aconst_null
      // 312: areturn
      // 313: iload 2
      // 314: ifne 31b
      // 317: bipush 1
      // 318: goto 31c
      // 31b: bipush 0
      // 31c: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 31f: new com/pnfsoftware/jebglobal/bfp$qa
      // 322: dup
      // 323: aload 0
      // 324: invokespecial com/pnfsoftware/jebglobal/bfp$qa.<init> (Lcom/pnfsoftware/jebglobal/bfp;)V
      // 327: areturn
      // 328: iload 2
      // 329: ifne 330
      // 32c: bipush 1
      // 32d: goto 331
      // 330: bipush 0
      // 331: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 334: new com/pnfsoftware/jebglobal/bfp$kY
      // 337: dup
      // 338: aload 0
      // 339: invokespecial com/pnfsoftware/jebglobal/bfp$kY.<init> (Lcom/pnfsoftware/jebglobal/bfp;)V
      // 33c: areturn
      // 33d: iload 2
      // 33e: ifne 345
      // 341: bipush 1
      // 342: goto 346
      // 345: bipush 0
      // 346: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 349: aconst_null
      // 34a: areturn
      // 34b: iload 2
      // 34c: ifne 353
      // 34f: bipush 1
      // 350: goto 354
      // 353: bipush 0
      // 354: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 357: aconst_null
      // 358: areturn
      // 359: iload 2
      // 35a: ifne 361
      // 35d: bipush 1
      // 35e: goto 362
      // 361: bipush 0
      // 362: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 365: aconst_null
      // 366: areturn
      // 367: new com/pnfsoftware/jebglobal/bfp$SG
      // 36a: dup
      // 36b: aload 0
      // 36c: iload 2
      // 36d: iload 3
      // 36e: invokespecial com/pnfsoftware/jebglobal/bfp$SG.<init> (Lcom/pnfsoftware/jebglobal/bfp;ZZ)V
      // 371: areturn
      // 372: new com/pnfsoftware/jebglobal/bfp$oL
      // 375: dup
      // 376: aload 0
      // 377: iload 2
      // 378: iload 3
      // 379: invokespecial com/pnfsoftware/jebglobal/bfp$oL.<init> (Lcom/pnfsoftware/jebglobal/bfp;ZZ)V
      // 37c: areturn
      // 37d: iload 2
      // 37e: ifne 385
      // 381: bipush 1
      // 382: goto 386
      // 385: bipush 0
      // 386: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 389: new com/pnfsoftware/jebglobal/bfp$GA
      // 38c: dup
      // 38d: aload 0
      // 38e: invokespecial com/pnfsoftware/jebglobal/bfp$GA.<init> (Lcom/pnfsoftware/jebglobal/bfp;)V
      // 391: areturn
      // 392: new com/pnfsoftware/jebglobal/bfp$Fw
      // 395: dup
      // 396: aload 0
      // 397: iload 2
      // 398: iload 3
      // 399: invokespecial com/pnfsoftware/jebglobal/bfp$Fw.<init> (Lcom/pnfsoftware/jebglobal/bfp;ZZ)V
      // 39c: areturn
      // 39d: new com/pnfsoftware/jebglobal/bfp$ej
      // 3a0: dup
      // 3a1: aload 0
      // 3a2: iload 2
      // 3a3: invokespecial com/pnfsoftware/jebglobal/bfp$ej.<init> (Lcom/pnfsoftware/jebglobal/bfp;Z)V
      // 3a6: areturn
      // 3a7: new com/pnfsoftware/jebglobal/bfp$CI
      // 3aa: dup
      // 3ab: aload 0
      // 3ac: iload 2
      // 3ad: invokespecial com/pnfsoftware/jebglobal/bfp$CI.<init> (Lcom/pnfsoftware/jebglobal/bfp;Z)V
      // 3b0: areturn
      // 3b1: new com/pnfsoftware/jebglobal/bfp$EE
      // 3b4: dup
      // 3b5: aload 0
      // 3b6: iload 2
      // 3b7: invokespecial com/pnfsoftware/jebglobal/bfp$EE.<init> (Lcom/pnfsoftware/jebglobal/bfp;Z)V
      // 3ba: areturn
      // 3bb: iload 2
      // 3bc: ifne 3c3
      // 3bf: bipush 1
      // 3c0: goto 3c4
      // 3c3: bipush 0
      // 3c4: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 3c7: new com/pnfsoftware/jebglobal/bfp$Vj
      // 3ca: dup
      // 3cb: aload 0
      // 3cc: invokespecial com/pnfsoftware/jebglobal/bfp$Vj.<init> (Lcom/pnfsoftware/jebglobal/bfp;)V
      // 3cf: areturn
      // 3d0: iload 2
      // 3d1: ifne 3d8
      // 3d4: bipush 1
      // 3d5: goto 3d9
      // 3d8: bipush 0
      // 3d9: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 3dc: aconst_null
      // 3dd: areturn
      // 3de: iload 2
      // 3df: ifne 3e6
      // 3e2: bipush 1
      // 3e3: goto 3e7
      // 3e6: bipush 0
      // 3e7: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 3ea: aconst_null
      // 3eb: areturn
      // 3ec: iload 2
      // 3ed: ifne 3f4
      // 3f0: bipush 1
      // 3f1: goto 3f5
      // 3f4: bipush 0
      // 3f5: invokestatic com/pnfsoftware/jeb/util/base/Assert.a (Z)V
      // 3f8: aconst_null
      // 3f9: areturn
      // 3fa: new java/lang/RuntimeException
      // 3fd: dup
      // 3fe: invokespecial java/lang/RuntimeException.<init> ()V
      // 401: athrow
      // 402: new com/pnfsoftware/jebglobal/bfp$Nz
      // 405: dup
      // 406: aload 0
      // 407: iload 2
      // 408: iload 1
      // 409: invokespecial com/pnfsoftware/jebglobal/bfp$Nz.<init> (Lcom/pnfsoftware/jebglobal/bfp;ZI)V
      // 40c: areturn
      // 40d: new java/lang/RuntimeException
      // 410: dup
      // 411: invokespecial java/lang/RuntimeException.<init> ()V
      // 414: athrow
      // 415: new com/pnfsoftware/jebglobal/bfp$Uz
      // 418: dup
      // 419: aload 0
      // 41a: iload 2
      // 41b: iload 1
      // 41c: invokespecial com/pnfsoftware/jebglobal/bfp$Uz.<init> (Lcom/pnfsoftware/jebglobal/bfp;ZI)V
      // 41f: areturn
      // 420: new com/pnfsoftware/jebglobal/bfp$eo
      // 423: dup
      // 424: aload 0
      // 425: iload 2
      // 426: iload 1
      // 427: invokespecial com/pnfsoftware/jebglobal/bfp$eo.<init> (Lcom/pnfsoftware/jebglobal/bfp;ZI)V
      // 42a: areturn
      // 42b: new com/pnfsoftware/jebglobal/bfp$LR
      // 42e: dup
      // 42f: aload 0
      // 430: iload 2
      // 431: iload 3
      // 432: ifeq 443
      // 435: aload 0
      // 436: getfield com/pnfsoftware/jebglobal/bfp.q Lcom/pnfsoftware/jebglobal/bfr;
      // 439: getfield com/pnfsoftware/jebglobal/bfr.Uv Lcom/pnfsoftware/jebglobal/bex;
      // 43c: ifnull 443
      // 43f: bipush 1
      // 440: goto 444
      // 443: bipush 0
      // 444: invokespecial com/pnfsoftware/jebglobal/bfp$LR.<init> (Lcom/pnfsoftware/jebglobal/bfp;ZZ)V
      // 447: areturn
      // 448: aconst_null
      // 449: areturn
   }

   interface Bu {
      void q();

      void RF();
   }

   class CI extends bfp.oM {
      CI(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         long var1 = bfp.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            bew var5 = bfp.this.q.q(bfo.nY.q());
            var5.q("isCanonical", this.Dw);
            var5.q("value", bfp.this.q.za());
            bfp.this.q.q(var5);
         }
      }

      @Override
      public void RF() {
      }
   }

   class CU extends bfp.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.q = bfp.this.q.jq;
         long var1 = bfp.this.q.nf();
         Longs.range(var1).forEach(var1x -> {
            bew var2 = bfp.this.q.q(bfo.oW.q());
            var2.q("id", bfp.this.q.oW());
            bfp.this.q.q(var2);
         });
         this.RF = bfp.this.q.jq;
         this.q(bfo.oW.q());
      }

      @Override
      public void RF() {
         int[][] var1 = new int[][]{{this.q, this.RF}, {this.oW, this.gO}};

         for (int[] var5 : var1) {
            for (int var6 = var5[0]; var6 < var5[1]; var6++) {
               boolean var7 = var6 < this.RF;
               bew var8 = bfp.this.q.xK(var6);
               this.q(var8);
               int var9 = bfp.this.q.oW();
               if (var7) {
                  Assert.a(var8.xK("id").equals(var9));
               } else {
                  var8.q("id", var9);
               }

               if (!bfp.this.q.PV && !bfp.this.q.io) {
                  var8.q("kernelOffset", bfp.this.q.gP());
               }

               var8.q("hostInstanceSizeInWords", bfp.this.q.za());
               var8.q("hostNextFieldOffsetInWords", bfp.this.q.za());
               var8.q("hostTypeArgumentsFieldOffsetInWords", bfp.this.q.za());
               if (!bfp.this.q.PV) {
                  var8.q("targetInstanceSizeInWords", var8.xK("hostInstanceSizeInWords"));
                  var8.q("targetNextFieldOffsetInWords", var8.xK("hostNextFieldOffsetInWords"));
                  var8.q("targetTypeArgumentsFieldOffsetInWords", var8.xK("hostTypeArgumentsFieldOffsetInWords"));
               }

               var8.q("numTypeArguments", bfp.this.q.za());
               var8.q("numNativeFields", bfp.this.q.nf());
               if (!bfp.this.q.PV) {
                  Assert.a(!bfp.this.q.io);
                  var8.q("tokenPos", bfp.this.q.gO());
                  var8.q("endTokenPos", bfp.this.q.gO());
               }

               var8.q("stateBits", bfp.this.q.nf());
               if (bfp.this.q.PV) {
                  if (var7) {
                     bfp.this.q.nf();
                  } else if (!bfp.this.q.gP.q(var9)) {
                     bfp.this.q.TX.put(var9, bfp.this.q.nf());
                  }
               }

               bfp.this.q.ui.put(var9, var8);
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bfp.this.q.nf());
         if (!bfp.this.q.Me) {
            var1.q("userName", bfp.this.q.nf());
         }

         var1.q("functions", bfp.this.q.nf());
         var1.q("functionsHashTable", bfp.this.q.nf());
         var1.q("fields", bfp.this.q.nf());
         var1.q("offsetInWordsToField", bfp.this.q.nf());
         var1.q("interfaces", bfp.this.q.nf());
         var1.q("script", bfp.this.q.nf());
         var1.q("library", bfp.this.q.nf());
         var1.q("typeParameters", bfp.this.q.nf());
         var1.q("superType", bfp.this.q.nf());
         var1.q("constants", bfp.this.q.nf());
         var1.q("declarationType", bfp.this.q.nf());
         var1.q("invocationDispatcherCache", bfp.this.q.nf());
         if (!bfp.this.q.Me || !bfp.this.q.PV) {
            var1.q("directImplementors", bfp.this.q.nf());
            var1.q("directSubclasses", bfp.this.q.nf());
         }

         if (!bfp.this.q.PV) {
            var1.q("allocationStub", bfp.this.q.nf());
            var1.q("dependentCode", bfp.this.q.nf());
         }
      }
   }

   class EE extends bfp.oM {
      EE(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bfo.lF.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            var2.q("value", bfp.this.q.za());
         }
      }
   }

   class FL extends bfp.oM {
      @Override
      public void q() {
         this.q(bfo.mI.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            this.q(var2);
            var2.q("canPatchToMonomorphic", bfp.this.q.Dw());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bfp.this.q.nf());
         var1.q("argsDescriptor", bfp.this.q.nf());
      }
   }

   class Fw extends bfp.oM {
      Fw(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bfo.CY.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
            var2.q("parameterizedClassId", bfp.this.q.qa());
            var2.q("base", bfp.this.q.zz());
            var2.q("index", bfp.this.q.zz());
            long var3 = bfp.this.q.zz();
            var2.q("typeState", var3 >> (int)bfp.this.q.gP.Rr);
            var2.q("nullability", var3 & bfp.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bfp.this.q.nf());
         var1.q("hash", bfp.this.q.nf());
         var1.q("bound", bfp.this.q.nf());
      }
   }

   class GA extends bfp.oM {
      @Override
      public void q() {
         this.q(bfo.AB.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bfp.this.q.nf());
         var1.q("type", bfp.this.q.nf());
      }
   }

   class HA extends bfp.oM {
      @Override
      public void q() {
         this.q(bfo.JY.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            this.q(var2);
            if (!bfp.this.q.PV) {
               var2.q("flagsAndMaxPosition", bfp.this.q.qa());
            }

            var2.q("kernelScriptIndex", bfp.this.q.qa());
            var2.q("loadTimestamp", 0);
         }
      }

      void q(bew var1) {
         var1.q("url", bfp.this.q.nf());
         if (bfp.this.q.io) {
            if (!bfp.this.q.Me) {
               var1.q("resolvedUrl", bfp.this.q.nf());
            }
         } else {
            var1.q("resolvedUrl", bfp.this.q.nf());
            var1.q("resolvedUrl", bfp.this.q.nf());
            var1.q("lineStarts", bfp.this.q.nf());
            var1.q("constantCoverage", bfp.this.q.nf());
            var1.q("debugPositions", bfp.this.q.nf());
            var1.q("kernelProgramInfo", bfp.this.q.nf());
         }
      }
   }

   class KZ extends bfp.oM {
      @Override
      public void q() {
         this.RF(bfo.KT.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bfp.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }

   class LR extends bfp.oM {
      LR(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.oW = bfp.this.q.jq;
         long var1 = bfp.this.q.nf();

         for (int var3 = 0; var3 < var1; var3++) {
            long var4 = bfp.this.q.nf();
            bfo var6 = (var4 & 1L) != 0L ? bfo.Qu : bfo.ND;
            long var7 = var4 >> 1;
            bew var9 = bfp.this.q.q(var6.q());
            var9.q("length", var7);
            bfp.this.q.q(var9);
         }

         this.gO = bfp.this.q.jq;
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            long var3 = bfp.this.q.nf();
            long var5 = var3 >> 1;
            bfo var7 = (var3 & 1L) != 0L ? bfo.Qu : bfo.ND;
            Assert.a(var5 <= 2147483647L);
            Assert.a(var2.RF() == (int)var5);
            Assert.a(var2.RF == var7.q());
            if (var7 == bfo.ND) {
               byte[] var11 = new byte[(int)var5];

               for (int var13 = 0; var13 < var11.length; var13++) {
                  byte var16 = (byte)bfp.this.q.zz();
                  var11[var13] = var16;
               }

               String var14 = Strings.decodeUTF8(var11);
               var2.q("data", var14);
            } else {
               int[] var8 = new int[(int)var5];

               for (int var9 = 0; var9 < (int)var5; var9++) {
                  int var10 = bfp.this.q.zz();
                  var10 |= bfp.this.q.zz() << 8;
                  var8[var9] = var10;
               }

               String var12 = new String(var8, 0, var8.length);
               var2.q("data", var12);
            }
         }
      }
   }

   class ME extends bfp.oM {
      ME(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.RF(bfo.ZT.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("hash", bfp.this.q.lm());
            var2.q("nullabity", bfp.this.q.nf());
            var2.q("instantiations", bfp.this.q.Uv());
            ArrayList var3 = new ArrayList();
            var2.q("types", var3);

            for (int var4 = 0; var4 < var2.RF(); var4++) {
               var3.add(bfp.this.q.Uv());
            }
         }
      }
   }

   class Nt extends bfp.oM {
      int q;
      int RF;

      @Override
      public void q() {
         this.oW = bfp.this.q.jq;
         long var1 = bfp.this.q.nf();

         for (long var3 = 0L; var3 < var1; var3++) {
            this.Dw();
         }

         this.gO = bfp.this.q.jq;
         this.q = bfp.this.q.jq;
         var1 = bfp.this.q.nf();

         for (long var6 = 0L; var6 < var1; var6++) {
            this.Dw();
         }

         this.RF = bfp.this.q.jq;
      }

      private void Dw() {
         int var1 = bfp.this.q.qa();
         boolean var2 = (var1 >> 3 & 1) == 1;
         if (var2) {
            bew var3 = bfp.this.q.q(bfo.Hk.q());
            var3.q("unknown", true);
            bfp.this.q.q(var3);
         } else {
            bew var4 = bfp.this.q.q(bfo.Hk.q());
            bfp.this.q.q(var4);
            var4.q("stateBits", var1);
         }
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            this.q(var1, false);
         }

         for (int var2 = this.q; var2 < this.RF; var2++) {
            this.q(var2, true);
         }
      }

      void q(int var1, boolean var2) {
         bew var3 = bfp.this.q.xK(var1);
         if (bfp.this.q.PV && Boolean.TRUE.equals(var3.q("unknown", Boolean.class))) {
            this.q(var3, var2, true);
         } else {
            this.q(var3, var2, false);
            if (bfp.this.q.io && bfp.this.q.oQ) {
               var3.q("objectPool", null);
            } else {
               var3.q("objectPool", bfp.this.q.Uv());
            }

            var3.q("owner", bfp.this.q.Uv());
            var3.q("exceptionHandlers", bfp.this.q.Uv(), bfo.sH.toString());
            var3.q("pcDescriptors", bfp.this.q.Uv());
            var3.q("catchEntry", bfp.this.q.Uv());
            var3.q("compressedStackMaps", bfp.this.q.Uv());
            var3.q("inlinedIdToFunction", bfp.this.q.Uv());
            var3.q("codeSourceMap", bfp.this.q.Uv());
            if (!bfp.this.q.PV && bfp.this.q.LK) {
               var3.q("deoptInfoArray", bfp.this.q.Uv());
               var3.q("staticCallsTargetTable", bfp.this.q.Uv());
            }

            if (!bfp.this.q.Me) {
               var3.q("returnAddressMetadata", bfp.this.q.Uv());
               var3.q("varDescriptors", null);
               var3.q("comments", bfp.this.q.Gf ? bfp.this.q.Uv() : null);
               var3.q("compileTimestamp", 0);
            }
         }
      }

      void q(bew var1, boolean var2, boolean var3) {
         if (var2) {
            Assert.a(!var3);
            throw new ToDoException();
         } else if (bfp.this.q.PV && bfp.this.q.oQ) {
            bfp.this.q.zx = (int)(bfp.this.q.zx + bfp.this.q.nf());
            long var4 = bfp.this.q.ZT + bfp.this.q.zx;
            long var6 = bfp.this.q.nf();
            long var8 = var6 >> 1;
            boolean var10 = (var6 & 1L) == 1L;
            long var11 = var10 ? bfp.this.q.gP.oQ : 0L;
            long var13 = var10 ? bfp.this.q.gP.PV : 0L;
            long var15 = var4 + var11;
            long var17 = var4 + var13;
            if (var3) {
               bfp.this.q.Uv();
            }

            if (!var3) {
               var1.q("entryPoint", var15);
               var1.q("uncheckedEntryPoint", var15 + var8);
               var1.q("monomorphicEntryPoint", var17);
               var1.q("monomorphicUncheckedEntryPoint", var17 + var8);
            }
         } else {
            throw new RuntimeException("Raw instructions deserialization missing");
         }
      }
   }

   class Nz extends bfp.oM {
      int q;

      Nz(boolean var2, int var3) {
         this.Dw = var2;
         this.q = var3;
      }

      @Override
      public void q() {
         this.q(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bfp.this.q.nf());
         var1.q("hashMask", bfp.this.q.nf());
         var1.q("data", bfp.this.q.nf());
         var1.q("usedData", bfp.this.q.nf());
         var1.q("deletedKeys", bfp.this.q.nf());
      }
   }

   class PY extends bfp.oM {
      @Override
      public void q() {
         this.q(bfo.zz.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            this.q(var2);
            if (!bfp.this.q.io) {
               var2.q("guardedListLength", bfp.this.q.Uv());
            }

            if (bfp.this.q.LK) {
               var2.q("dependentCode", bfp.this.q.Uv());
            }

            if (!bfp.this.q.io) {
               var2.q("tokenPos", bfp.this.q.gO());
               var2.q("endTokenPos", bfp.this.q.gO());
               var2.q("guardedCid", bfp.this.q.oW());
               var2.q("isNullable", bfp.this.q.oW());
               var2.q("staticTypeExactnessState", bfp.this.q.JY());
               if (!bfp.this.q.PV) {
                  var2.q("kernelOffset", bfp.this.q.gP());
               }
            }

            int var3 = bfp.this.q.gP();
            var2.q("kindBits", var3);
            long var4 = bfp.this.q.Uv();
            if (bfp.this.q.Uv(var3)) {
               long var6 = bfp.this.q.nf();
               var2.q("hostOffsetOrFieldId", var6);
            } else {
               var2.q("hostOffsetOrFieldId", var4);
               if (!bfp.this.q.PV) {
                  var2.q("targetOffset", var2.xK("hostOffsetOrFieldId"));
               }
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bfp.this.q.nf());
         var1.q("owner", bfp.this.q.nf());
         var1.q("type", bfp.this.q.nf());
         var1.q("initializerFunction", bfp.this.q.nf());
      }
   }

   class SG extends bfp.oM {
      SG(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bfo.GY.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("typeClassId", bfp.this.q.nf());
            long var3 = bfp.this.q.zz();
            var2.q("typeState", var3 >> (int)bfp.this.q.gP.Rr);
            var2.q("nullability", var3 & bfp.this.q.gP.EB);
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bfp.this.q.nf());
         var1.q("arguments", bfp.this.q.nf());
         var1.q("hash", bfp.this.q.nf());
      }
   }

   class Uz extends bfp.oM {
      int q;

      Uz(boolean var2, int var3) {
         this.Dw = var2;
         this.q = var3;
      }

      @Override
      public void q() {
         this.q(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            this.q(var2);
         }
      }

      private void q(bew var1) {
         var1.q("typeArguments", bfp.this.q.nf());
         var1.q("hashMask", bfp.this.q.nf());
         var1.q("data", bfp.this.q.nf());
         var1.q("usedData", bfp.this.q.nf());
         var1.q("deletedKeys", bfp.this.q.nf());
      }
   }

   class Vj extends bfp.oM {
      @Override
      public void q() {
         this.q(bfo.cR.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("typeArguments", bfp.this.q.nf());
         var1.q("length", bfp.this.q.nf());
         var1.q("data", bfp.this.q.nf());
      }
   }

   class Xa extends bfp.oM {
      @Override
      public void q() {
         this.q(bfo.Rr.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            this.q(var2);
            var2.q("filledEntryCount", bfp.this.q.lm());
         }
      }

      void q(bew var1) {
         var1.q("targetName", bfp.this.q.nf());
         var1.q("argsDescriptor", bfp.this.q.nf());
         var1.q("buckets", bfp.this.q.nf());
         var1.q("mask", bfp.this.q.nf());
      }
   }

   class bK extends bfp.oM {
      int q;
      int RF;
      int xK;

      bK(int var2, boolean var3) {
         this.q = var2;
         this.Dw = var3;
      }

      @Override
      public void q() {
         this.oW = bfp.this.q.jq;
         long var1 = bfp.this.q.nf();
         this.RF = bfp.this.q.qa();
         this.xK = bfp.this.q.qa();
         bfp.this.q.q(var1, this.q);
         this.gO = bfp.this.q.jq;
      }

      @Override
      public void RF() {
         int var1 = this.RF << (int)bfp.this.q.gP.nf;
         int var2 = (int)bfp.this.q.q(this.xK * bfp.this.q.gP.gO, bfp.this.q.gP.gP);
         Long var3 = bfp.this.q.nf();

         for (int var4 = this.oW; var4 < this.gO; var4++) {
            bew var5 = bfp.this.q.xK(var4);
            var5.q("isCanonical", this.Dw);

            int var6;
            for (var6 = bfp.this.q.wF ? 8 : 4; var6 < var1; var6 = (int)(var6 + bfp.this.q.gP.gO)) {
               if (bfp.this.q.RF(var3, var6 / (int)bfp.this.q.gP.gO)) {
                  bfp.this.q.oQ();
               } else {
                  bfp.this.q.Uv();
               }
            }

            while (var6 < var2) {
               var6 = (int)(var6 + bfp.this.q.gP.gO);
            }

            Assert.a(var6 == var2);
         }
      }
   }

   class ct extends bfp.oM {
      @Override
      public void q() {
         this.q(bfo.CY.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("names", bfp.this.q.nf());
         var1.q("flags", bfp.this.q.nf());
         var1.q("bounds", bfp.this.q.nf());
         var1.q("defaults", bfp.this.q.nf());
      }
   }

   class eM extends bfp.oM {
      int q;

      eM(boolean var2, boolean var3, int var4) {
         this.Dw = var2;
         this.Uv = var3;
         this.q = var4;
      }

      @Override
      public void q() {
         this.oW = bfp.this.q.jq;
         long var1 = bfp.this.q.nf();
         int var3 = 0;

         for (long var4 = 0L; var4 < var1; var4++) {
            var3 = (int)(var3 + (bfp.this.q.nf() << (int)bfp.this.q.gP.za));
            Object var6 = this.xK(var3);
            bew var7 = bfp.this.q.q(this.q);
            var7.q("data", var6);
            bfp.this.q.q(var7);
         }

         this.gO = bfp.this.q.jq;
         if (this.q == bfo.eC.q()) {
            this.xK();
         }
      }

      @Override
      public void RF() {
      }

      protected Object xK(int var1) {
         if (this.q != bfo.ND.q() && this.q != bfo.eC.q()) {
            return Strings.ff("ROData_object_at_0x%X", var1);
         } else {
            bfp.this.q.gO.position(var1);
            bfp.this.q.gO.i32();
            long var2;
            if (bfp.this.q.wF) {
               bfp.this.q.gO.i32();
               var2 = bfp.this.q.gO.i64();
            } else {
               var2 = bfp.this.q.gO.i32();
               bfp.this.q.gO.i32();
            }

            Assert.a(var2 <= 2147483647L);
            byte[] var4 = bfp.this.q.gO.get((int)var2 / 2);
            return Strings.decodeASCII(var4);
         }
      }
   }

   class ej extends bfp.oM {
      ej(boolean var2) {
         this.Dw = var2;
      }

      @Override
      public void q() {
         this.q(bfo.WI.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            var2.q("isCanonical", this.Dw);
            this.q(var2);
         }
      }

      void q(bew var1) {
         var1.q("instantiatorTypeArguments", bfp.this.q.nf());
         var1.q("functionTypeArguments", bfp.this.q.nf());
         var1.q("delayedTypeArguments", bfp.this.q.nf());
         var1.q("function", bfp.this.q.nf());
         var1.q("context", bfp.this.q.nf());
         var1.q("hash", bfp.this.q.nf());
      }
   }

   class eo extends bfp.oM {
      int q;

      eo(boolean var2, int var3) {
         this.Dw = var2;
         this.q = var3;
      }

      @Override
      public void q() {
         this.RF(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            var2.q("typeArguments", bfp.this.q.Uv());
            Object[] var4 = new Object[var3];

            for (int var5 = 0; var5 < var3; var5++) {
               var4[var5] = bfp.this.q.Uv();
            }

            var2.q("data", var4);
         }
      }
   }

   class iZ extends bfp.oM {
      @Override
      public void q() {
         this.RF(bfo.Gf.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            int var3 = this.RF(var2);
            byte[] var4 = bfp.this.q.Dw(var3);
            var2.q("data", var4);
         }
      }
   }

   class kY extends bfp.oM {
      @Override
      public void q() {
         this.q(bfo.Xo.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            var2.q("parent", bfp.this.q.Uv());
            var2.q("baseObjects", null);
            var2.q("id", bfp.this.q.qa());
            var2.q("loaded", false);
            var2.q("loadOutstanding", false);
         }
      }
   }

   class nI extends bfp.oM {
      @Override
      public void q() {
         this.q(bfo.za.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            if (bfp.this.q.io) {
               var2.q("contextScope", null);
            } else {
               var2.q("contextScope", bfp.this.q.Uv());
            }

            var2.q("parentFunction", bfp.this.q.Uv());
            var2.q("closure", bfp.this.q.Uv());
            var2.q("defaultTypeArgumentsKind", bfp.this.q.nf());
         }
      }
   }

   class oL extends bfp.oM {
      oL(boolean var2, boolean var3) {
         this.Dw = var2;
         this.Uv = var3;
      }

      @Override
      public void q() {
         this.q(bfo.Wx.q());
         this.xK();
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            this.q(var2);
            var2.q("isCanonical", this.Dw);
            long var3 = bfp.this.q.zz();
            var2.q("typeState", var3 >> (int)bfp.this.q.gP.Rr);
            var2.q("nullability", var3 & bfp.this.q.gP.EB);
            var2.q("packedParameterCounts", bfp.this.q.io());
            var2.q("packedTypeParameterCounts", bfp.this.q.HF());
         }
      }

      void q(bew var1) {
         var1.q("typeTestStub", bfp.this.q.nf());
         var1.q("typeParameters", bfp.this.q.nf());
         var1.q("resultType", bfp.this.q.nf());
         var1.q("parameterTypes", bfp.this.q.nf());
         var1.q("namedParameterNames", bfp.this.q.nf());
         var1.q("hash", bfp.this.q.nf());
      }
   }

   abstract class oM implements bfp.Bu {
      boolean Dw;
      boolean Uv;
      int oW;
      int gO;

      @Override
      public void q() {
         throw new ToDoException("alloc() for " + this.getClass().getSimpleName());
      }

      @Override
      public void RF() {
         throw new ToDoException("fill() for " + this.getClass().getSimpleName());
      }

      protected final void q(int var1) {
         this.oW = bfp.this.q.jq;
         long var2 = bfp.this.q.nf();
         bfp.this.q.q(var2, var1);
         this.gO = bfp.this.q.jq;
      }

      protected final void RF(int var1) {
         this.oW = bfp.this.q.jq;
         long var2 = bfp.this.q.nf();
         Longs.range(var2).forEach(var2x -> {
            bew var3 = bfp.this.q.q(var1);
            long var4 = bfp.this.q.nf();
            var3.q("length", var4);
            bfp.this.q.q(var3);
         });
         this.gO = bfp.this.q.jq;
      }

      protected final int RF(bew var1) {
         long var2 = bfp.this.q.nf();
         Assert.a(var2 <= 2147483647L);
         Assert.a(var1.RF() == (int)var2);
         return (int)var2;
      }

      protected final void xK() {
         if (this.Uv && this.Dw) {
            bfp.this.q.nf();
            long var1 = bfp.this.q.nf();

            for (int var3 = this.oW + (int)var1; var3 < this.gO; var3++) {
               bfp.this.q.nf();
            }
         }
      }

      @Override
      public String toString() {
         return Strings.ff("Cluster %s", this.getClass().getSimpleName());
      }
   }

   class qV extends bfp.oM {
      @Override
      public void q() {
         this.RF(bfo.sH.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("numEntries", var3);
            var2.q("handledTypesData", bfp.this.q.Uv());
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var3; var5++) {
               HashMap var6 = new HashMap();
               var6.put("handlerPcOffset", bfp.this.q.gP());
               var6.put("outerTryIndex", bfp.this.q.LK());
               var6.put("needsStacktrace", bfp.this.q.Dw());
               var6.put("hasCatchAll", bfp.this.q.Dw());
               var6.put("isGenerated", bfp.this.q.Dw());
               var4.add(var6);
            }
         }
      }
   }

   class qa extends bfp.oM {
      @Override
      public void q() {
         this.q(bfo.EB.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            var2.q("cache", bfp.this.q.Uv());
         }
      }
   }

   class qx extends bfp.oM {
      int q;

      public qx(int var2) {
         this.q = var2;
      }

      @Override
      public void q() {
         this.RF(this.q);
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            int var3 = this.RF(var2);
            var2.q("isCanonical", this.Dw);
            int var4 = var3 * bfp.this.q.HF(this.q);
            var2.q("data", bfp.this.q.Dw(var4));
         }
      }
   }

   class ry extends bfp.oM {
      @Override
      public void q() {
      }

      @Override
      public void RF() {
      }
   }

   class tl extends bfp.oM {
      @Override
      public void q() {
         this.q(bfo.HF.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            this.q(var2);
            var2.q("nativeEntryResolver", null);
            var2.q("nativeEntrySymbolResolver", null);
            var2.q("index", bfp.this.q.qa());
            var2.q("numImports", bfp.this.q.HF());
            var2.q("loadState", bfp.this.q.JY());
            var2.q("flags", bfp.this.q.zz());
            if (!bfp.this.q.PV && !bfp.this.q.io) {
               var2.q("kernelOffset", bfp.this.q.io());
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bfp.this.q.nf());
         var1.q("url", bfp.this.q.nf());
         var1.q("privateKey", bfp.this.q.nf());
         var1.q("dictionary", bfp.this.q.nf());
         var1.q("metadata", bfp.this.q.nf());
         var1.q("toplevelClass", bfp.this.q.nf());
         var1.q("usedScripts", bfp.this.q.nf());
         var1.q("loadingUnit", bfp.this.q.nf());
         var1.q("imports", bfp.this.q.nf());
         var1.q("exports", bfp.this.q.nf());
         if (!bfp.this.q.io) {
            var1.q("dependencies", bfp.this.q.nf());
            var1.q("kernelData", bfp.this.q.nf());
         }
      }
   }

   class tw extends bfp.oM {
      @Override
      public void q() {
         this.RF(bfo.Ef.q());
      }

      @Override
      public void RF() {
         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            long var3 = bfp.this.q.nf();
            int var5 = (int)(var3 >>> 2);
            Assert.a(var5 == var2.RF());
            byte[] var6 = bfp.this.q.Dw(var5);
            var2.q("data", var6);
         }
      }
   }

   class vX extends bfp.oM {
      @Override
      public void q() {
         this.RF(bfo.xW.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            this.RF(var2);
            ArrayList var3 = new ArrayList();
            var2.q("entryBits", var3);
            ArrayList var4 = new ArrayList();
            var2.q("data", var4);

            for (int var5 = 0; var5 < var2.RF(); var5++) {
               int var6 = bfp.this.q.zz();
               var3.add(var6);
               HashMap var7 = new HashMap();
               int var8 = bfp.this.q.oW(var6);
               if (var8 == bfp.this.q.gP.xW) {
                  var7.put("rawObj", bfp.this.q.Uv());
               } else if (var8 == bfp.this.q.gP.KT) {
                  var7.put("rawValue", bfp.this.q.za());
               } else if (var8 == bfp.this.q.gP.Gf) {
                  var7.put("rawValue", "lazy link entry");
               } else {
                  if (var8 != bfp.this.q.gP.sH && var8 != bfp.this.q.gP.CE) {
                     throw new RuntimeException("No type associated to decoded type bits");
                  }

                  Assert.a(bfp.this.q.PV && bfp.this.q.oQ);
                  var7.put("rawValue", "unsupported");
               }

               var4.add(var7);
            }
         }
      }
   }

   class vb extends bfp.oM {
      @Override
      public void q() {
         this.q(bfo.nf.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            this.q(var2);
            if (bfp.this.q.HF) {
               throw new ToDoException();
            }

            if (!bfp.this.q.io) {
               throw new ToDoException();
            }

            var2.q("codeIndex", bfp.this.q.nf());
            var2.q("code", var2.xK("codeIndex"));
            if (!bfp.this.q.PV && !bfp.this.q.io) {
               var2.q("positionalParameterNames", bfp.this.q.Uv());
               var2.q("tokenPos", bfp.this.q.gO());
               var2.q("endTokenPos", bfp.this.q.gO());
               var2.q("kernelOffset", bfp.this.q.nf());
            }

            var2.q("packedFields", bfp.this.q.nf());
            var2.q("kindTag", bfp.this.q.nf());
            if (!bfp.this.q.PV) {
               var2.q("usageCounter", 0);
               var2.q("optimizedInstructionCount", 0);
               var2.q("optimizedCallSiteCount", 0);
               var2.q("deoptimizationCounter", 0);
               var2.q("stateBits", 0);
               var2.q("inliningDepth", 0);
            }
         }
      }

      void q(bew var1) {
         var1.q("name", bfp.this.q.nf());
         var1.q("owner", bfp.this.q.nf());
         var1.q("signature", bfp.this.q.nf());
         var1.q("data", bfp.this.q.nf());
      }
   }

   class vn extends bfp.oM {
      @Override
      public void q() {
         this.q(bfo.lm.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            this.q(var2);
            var2.q("callbackId", bfp.this.q.io ? bfp.this.q.nf() : 0L);
         }
      }

      void q(bew var1) {
         var1.q("signatureType", bfp.this.q.nf());
         var1.q("cSignature", bfp.this.q.nf());
         var1.q("callbackTarget", bfp.this.q.nf());
         var1.q("callbackExceptionalReturn", bfp.this.q.nf());
      }
   }

   class zJ extends bfp.oM {
      @Override
      public void q() {
         this.q(bfo.gO.q());
      }

      @Override
      public void RF() {
         Assert.a(!this.Dw);

         for (int var1 = this.oW; var1 < this.gO; var1++) {
            bew var2 = bfp.this.q.xK(var1);
            this.q(var2);
            if (!bfp.this.q.PV && !bfp.this.q.io) {
               var2.q("libraryKernelOffset", bfp.this.q.qa());
            }
         }
      }

      void q(bew var1) {
         var1.q("patchedClass", bfp.this.q.nf());
         var1.q("originClass", bfp.this.q.nf());
         var1.q("script", bfp.this.q.nf());
         if (!bfp.this.q.io) {
            var1.q("libraryKernelData", bfp.this.q.nf());
         }
      }
   }
}
