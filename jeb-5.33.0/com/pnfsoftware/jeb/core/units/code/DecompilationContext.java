package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.util.base.IProgressCallback;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class DecompilationContext {
   private DecompilationOptions opt;
   private IProgressCallback[] acallback = new IProgressCallback[1];
   private AtomicBoolean interruptionRequested = new AtomicBoolean();
   private Map datamap = new ConcurrentHashMap();
   private Map errormap = new ConcurrentHashMap();
   private Map resmap = new ConcurrentHashMap();
   private AtomicInteger deferredRequestCount = new AtomicInteger();
   private Map map1 = new ConcurrentHashMap();
   private Map map2 = new ConcurrentHashMap();
   private AtomicInteger counter1 = new AtomicInteger();
   private AtomicInteger counter2 = new AtomicInteger();

   public static DecompilationContext safe(DecompilationContext var0) {
      return var0 != null ? var0 : new DecompilationContext(DecompilationOptions.DEFAULT);
   }

   public DecompilationContext() {
      this(null);
   }

   public DecompilationContext(DecompilationOptions var1) {
      this.opt = DecompilationOptions.safe(var1);
   }

   public DecompilationContext(int var1, Long var2, Long var3) {
      this.opt = new DecompilationOptions(var1, var2, var3, null);
   }

   public DecompilationContext(int var1) {
      this(var1, null, null);
   }

   public DecompilationContext fork() {
      DecompilationContext var1 = new DecompilationContext();
      var1.opt = this.opt;
      var1.acallback = this.acallback;
      var1.interruptionRequested = this.interruptionRequested;
      var1.datamap = this.datamap;
      var1.errormap = this.errormap;
      var1.resmap = this.resmap;
      var1.deferredRequestCount = this.deferredRequestCount;
      var1.map1 = this.map1;
      var1.map2 = this.map2;
      var1.counter1 = this.counter1;
      var1.counter2 = this.counter2;
      return var1;
   }

   public DecompilationOptions getOptions() {
      return this.opt;
   }

   public DecompilationOptions setOptions(DecompilationOptions var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         DecompilationOptions var2 = this.opt;
         this.opt = var1;
         return var2;
      }
   }

   public IProgressCallback getCallback() {
      return this.acallback[0];
   }

   public void setCallback(IProgressCallback var1) {
      this.acallback[0] = var1;
   }

   public DecompilationOptions addFlags(int var1) {
      return this.setOptions(this.opt.asBuilder().flags(this.opt.getFlags() | var1).build());
   }

   public DecompilationOptions removeFlags(int var1) {
      return this.setOptions(this.opt.asBuilder().flags(this.opt.getFlags() & ~var1).build());
   }

   public int getFlags() {
      return this.opt.getFlags();
   }

   public boolean hasFlags(int var1) {
      return (this.opt.getFlags() & var1) == var1;
   }

   public Long getMaxTimePerMethod() {
      return this.opt.getMaxTimePerMethod();
   }

   public Long getMaxTimeTotal() {
      return this.opt.getMaxTimeTotal();
   }

   public void requestInterruption() {
      this.interruptionRequested.set(true);
   }

   public boolean isInterruptionRequested() {
      return this.interruptionRequested.get();
   }

   public void recordResult(String var1, DecompilationResult var2) {
      if (var2 == null) {
         this.resmap.remove(var1);
      } else {
         this.resmap.put(var1, var2);
      }
   }

   public DecompilationResult getResults(String var1) {
      return (DecompilationResult)this.resmap.get(var1);
   }

   public Map getResultMap() {
      return Collections.unmodifiableMap(this.resmap);
   }

   public void recordError(String var1, String var2) {
      if (var2 == null) {
         this.errormap.remove(var1);
      } else {
         this.errormap.put(var1, var2);
      }
   }

   public String getError(String var1) {
      return (String)this.errormap.get(var1);
   }

   public Map getErrorMap() {
      return Collections.unmodifiableMap(this.errormap);
   }

   public boolean hasErrors() {
      return !this.errormap.isEmpty();
   }

   public void putData(String var1, Object var2) {
      if (var2 == null) {
         this.datamap.remove(var1);
      } else {
         this.datamap.put(var1, var2);
      }
   }

   public Object getData(String var1) {
      return this.datamap.get(var1);
   }

   public int recordDeferredRequest() {
      return this.deferredRequestCount.incrementAndGet();
   }

   public boolean recordAndCheckDeferredRequests(int var1, int var2) {
      var1 = this.deferredRequestCount.addAndGet(var1);
      return var2 < 0 || var1 <= var2;
   }

   public Map getMap1() {
      return this.map1;
   }

   public Map getMap2() {
      return this.map2;
   }

   public int getCounter1() {
      return this.counter1.get();
   }

   public int incrementCounter1() {
      return this.counter1.incrementAndGet();
   }

   public int getCounter2() {
      return this.counter2.get();
   }

   public int incrementCounter2() {
      return this.counter2.incrementAndGet();
   }
}
