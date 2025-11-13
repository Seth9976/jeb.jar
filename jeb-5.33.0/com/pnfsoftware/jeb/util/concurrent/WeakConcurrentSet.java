package com.pnfsoftware.jeb.util.concurrent;

import java.util.Iterator;
import java.util.Map.Entry;

public class WeakConcurrentSet implements Iterable, Runnable {
   final WeakConcurrentMap target;

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public WeakConcurrentSet(WeakConcurrentSet.Cleaner var1) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.RuntimeException: invalid constant type: Lcom/pnfsoftware/jeb/util/concurrent/WeakConcurrentSet$Cleaner; with value 2
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ConstExprent.toJava(ConstExprent.java:356)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SwitchStatement.toJava(SwitchStatement.java:171)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.RootStatement.toJava(RootStatement.java:36)
      //   at org.jetbrains.java.decompiler.main.ClassWriter.writeMethod(ClassWriter.java:1326)
      //
      // Bytecode:
      // 00: aload 0
      // 01: invokespecial java/lang/Object.<init> ()V
      // 04: getstatic com/pnfsoftware/jeb/util/concurrent/WeakConcurrentSet$1.$SwitchMap$com$pnfsoftware$jeb$util$concurrent$WeakConcurrentSet$Cleaner [I
      // 07: aload 1
      // 08: invokevirtual com/pnfsoftware/jeb/util/concurrent/WeakConcurrentSet$Cleaner.ordinal ()I
      // 0b: iaload
      // 0c: tableswitch 68 1 3 28 42 42
      // 28: aload 0
      // 29: new com/pnfsoftware/jeb/util/concurrent/WeakConcurrentMap$WithInlinedExpunction
      // 2c: dup
      // 2d: invokespecial com/pnfsoftware/jeb/util/concurrent/WeakConcurrentMap$WithInlinedExpunction.<init> ()V
      // 30: putfield com/pnfsoftware/jeb/util/concurrent/WeakConcurrentSet.target Lcom/pnfsoftware/jeb/util/concurrent/WeakConcurrentMap;
      // 33: goto 58
      // 36: aload 0
      // 37: new com/pnfsoftware/jeb/util/concurrent/WeakConcurrentMap
      // 3a: dup
      // 3b: aload 1
      // 3c: getstatic com/pnfsoftware/jeb/util/concurrent/WeakConcurrentSet$Cleaner.THREAD Lcom/pnfsoftware/jeb/util/concurrent/WeakConcurrentSet$Cleaner;
      // 3f: if_acmpne 46
      // 42: bipush 1
      // 43: goto 47
      // 46: bipush 0
      // 47: invokespecial com/pnfsoftware/jeb/util/concurrent/WeakConcurrentMap.<init> (Z)V
      // 4a: putfield com/pnfsoftware/jeb/util/concurrent/WeakConcurrentSet.target Lcom/pnfsoftware/jeb/util/concurrent/WeakConcurrentMap;
      // 4d: goto 58
      // 50: new java/lang/AssertionError
      // 53: dup
      // 54: invokespecial java/lang/AssertionError.<init> ()V
      // 57: athrow
      // 58: return
   }

   public boolean add(Object var1) {
      return this.target.put(var1, Boolean.TRUE) == null;
   }

   public boolean contains(Object var1) {
      return this.target.containsKey(var1);
   }

   public boolean remove(Object var1) {
      return this.target.remove(var1) != null;
   }

   public void clear() {
      this.target.clear();
   }

   public int approximateSize() {
      return this.target.approximateSize();
   }

   @Override
   public void run() {
      this.target.run();
   }

   public void expungeStaleEntries() {
      this.target.expungeStaleEntries();
   }

   public Thread getCleanerThread() {
      return this.target.getCleanerThread();
   }

   @Override
   public Iterator iterator() {
      return new WeakConcurrentSet.ReducingIterator(this.target.iterator());
   }

   public static enum Cleaner {
      THREAD,
      INLINE,
      MANUAL;
   }

   private static class ReducingIterator implements Iterator {
      private final Iterator iterator;

      private ReducingIterator(Iterator var1) {
         this.iterator = var1;
      }

      @Override
      public void remove() {
         this.iterator.remove();
      }

      @Override
      public Object next() {
         return ((Entry)this.iterator.next()).getKey();
      }

      @Override
      public boolean hasNext() {
         return this.iterator.hasNext();
      }
   }
}
