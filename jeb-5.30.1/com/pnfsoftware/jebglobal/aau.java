package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CallGraphVertex;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICallGraph;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryModelEventType;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Ser
public class aau implements ICallGraph {
   private static final ILogger q = GlobalLog.getLogger(aau.class);
   @SerId(1)
   private aaf RF;
   @SerId(2)
   private adt xK;
   @SerId(3)
   private Map Dw;
   @SerId(4)
   private Map Uv;
   @SerId(5)
   private Map oW;

   public aau(aaf var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.RF = var1;
         this.xK = new adt();
         this.Dw = new ConcurrentHashMap();
         this.Uv = new ConcurrentHashMap();
         this.oW = new ConcurrentHashMap();
      }
   }

   @Override
   public void recordInternalCall(long var1, CodePointer var3, boolean var4) {
      try (ACLock var5 = this.RF.q.a()) {
         if (var3.isUnknownAddress()) {
            throw new IllegalArgumentException("callee entry-point is an unknown address");
         }

         CallGraphVertex var6 = this.q(var1, true);
         CallGraphVertex var7 = this.q(var3, true);
         if (var6 == null || var7 == null) {
            throw new JebRuntimeException("unknown vertex");
         }

         adr var8 = new adr(var6, var7);
         if (var4) {
            var8.q(true);
         }

         if (this.xK.q((ads)var8)) {
            this.RF.notifyListenersOfModelChange(MemoryModelEventType.REFERENCE_UPDATE, var1);
            this.RF.notifyListenersOfModelChange(MemoryModelEventType.REFERENCE_UPDATE, var3.getAddress());
         }
      }
   }

   @Override
   public void recordExternalCall(long var1, INativeMethodItem var3, boolean var4) {
      try (ACLock var5 = this.RF.q.a()) {
         CallGraphVertex var6 = this.q(var1, true);
         CallGraphVertex var7 = this.q(var3, true);
         if (var6 == null || var7 == null) {
            throw new JebRuntimeException("unknown vertex");
         }

         adr var8 = new adr(var6, var7);
         if (var4) {
            var8.q(true);
         }

         if (this.xK.q((ads)var8)) {
            this.RF.notifyListenersOfModelChange(MemoryModelEventType.REFERENCE_UPDATE, var1);
         }
      }
   }

   @Override
   public void recordUnresolvedCall(long var1, long var3, boolean var5) {
      try (ACLock var6 = this.RF.q.a()) {
         CallGraphVertex var7 = this.q(var1, true);
         CallGraphVertex var8 = this.RF(var3, true);
         if (var7 == null || var8 == null) {
            throw new JebRuntimeException("unknown vertex");
         }

         adr var9 = new adr(var7, var8);
         if (var5) {
            var9.q(true);
         }

         if (this.xK.q((ads)var9)) {
            this.RF.notifyListenersOfModelChange(MemoryModelEventType.REFERENCE_UPDATE, var1);
         }
      }
   }

   private CallGraphVertex q(CodePointer var1, boolean var2) {
      CallGraphVertex var3 = (CallGraphVertex)this.Dw.get(var1.getAddress());
      if (var3 == null && var2) {
         var3 = CallGraphVertex.buildFromInternalTarget(var1);
         this.Dw.put(var1.getAddress(), var3);
      }

      return var3;
   }

   private CallGraphVertex q(long var1, boolean var3) {
      CallGraphVertex var4 = (CallGraphVertex)this.Dw.get(var1);
      if (var4 == null && var3) {
         CodePointer var5 = CodePointer.createFrom(var1, this.RF);
         var4 = CallGraphVertex.buildFromInternalTarget(var5);
         this.Dw.put(var1, var4);
      }

      return var4;
   }

   private CallGraphVertex q(INativeMethodItem var1, boolean var2) {
      CallGraphVertex var3 = (CallGraphVertex)this.Uv.get(var1);
      if (var3 == null && var2) {
         var3 = CallGraphVertex.buildFromExternalTarget(var1);
         this.Uv.put(var1, var3);
      }

      return var3;
   }

   private CallGraphVertex RF(long var1, boolean var3) {
      CallGraphVertex var4 = (CallGraphVertex)this.oW.get(var1);
      if (var4 == null && var3) {
         var4 = CallGraphVertex.buildFromUnresolvedTarget(var1);
         this.oW.put(var1, var4);
      }

      return var4;
   }

   @Override
   public void removeCallRelationship(long var1) {
      try (ACLock var3 = this.RF.q.a()) {
         CallGraphVertex var4 = this.q(var1, false);
         if (var4 != null && this.xK.nf(var4)) {
            this.RF.notifyListenersOfModelChange(MemoryModelEventType.REFERENCE_UPDATE, var1);
         }
      }
   }

   @Override
   public List getCallees(long var1, boolean var3) {
      CallGraphVertex var4 = this.q(var1, false);
      if (var4 != null) {
         return var3 ? this.xK.RF(var4, "attrIsSafe") : this.xK.gO(var4);
      } else {
         return Collections.emptyList();
      }
   }

   @Override
   public List getCallees(INativeMethodItem var1, boolean var2) {
      if (var1 != null && var1.getData() != null) {
         return this.q(var1.getData(), var2);
      } else {
         throw new IllegalArgumentException("not an internal routine");
      }
   }

   public List q(INativeMethodDataItem var1, boolean var2) {
      Assert.a(var1 != null);
      ArrayList var3 = new ArrayList();

      for (AddressableInstruction var5 : var1.getCFG().addressableInstructions()) {
         CallGraphVertex var6 = this.q(var5.getOffset(), false);
         if (var6 != null) {
            if (var2) {
               var3.addAll(this.xK.RF(var6, "attrIsSafe"));
            } else {
               var3.addAll(this.xK.gO(var6));
            }
         }
      }

      return var3;
   }

   @Override
   public List getCalleeRoutines(long var1, boolean var3) {
      ArrayList var4 = new ArrayList();
      List var5 = this.getCallees(var1, var3);
      if (var5 != null) {
         for (CallGraphVertex var7 : var5) {
            Object var8 = null;
            if (var7.isInternal()) {
               var8 = this.RF.oW(var7.getInternalAddress().getAddress());
               if (var8 == null) {
                  INativeContinuousItem var9 = this.RF.getItemAt(var7.getInternalAddress().getAddress());
                  if (var9 instanceof axu) {
                     var8 = ((axu)var9).cC();
                  }
               }
            } else if (var7.isResolved()) {
               var8 = var7.getRoutine();
            } else {
               INativeContinuousItem var10 = this.RF.getItemAt(var7.getDereferencedAddress());
               if (var10 instanceof axu) {
                  var8 = ((axu)var10).cC();
               }
            }

            if (var8 != null) {
               var4.add(var8);
            }
         }
      }

      return var4;
   }

   private List q(List var1) {
      ArrayList var2 = new ArrayList();

      for (CallGraphVertex var4 : var1) {
         if (var4.isInternal()) {
            var2.add(var4.getInternalAddress().getAddress());
         }
      }

      return var2;
   }

   @Override
   public List getCallers(CallGraphVertex var1, boolean var2) {
      Assert.a(var1 != null);
      return var2 ? this.q(this.xK.q(var1, "attrIsSafe")) : this.q(this.xK.Uv(var1));
   }

   @Override
   public List getCallers(INativeMethodItem var1, boolean var2) {
      Assert.a(var1 != null);
      if (var1.getData() == null) {
         CallGraphVertex var3 = this.q(var1, false);
         if (var3 != null) {
            return var2 ? this.q(this.xK.q(var3, "attrIsSafe")) : this.q(this.xK.Uv(var3));
         } else {
            return Collections.emptyList();
         }
      } else {
         return this.RF(var1.getData(), var2);
      }
   }

   public List RF(INativeMethodDataItem var1, boolean var2) {
      CallGraphVertex var3 = this.q(var1.getMemoryAddress(), false);
      if (var3 != null) {
         return var2 ? this.q(this.xK.q(var3, "attrIsSafe")) : this.q(this.xK.Uv(var3));
      } else {
         return Collections.emptyList();
      }
   }

   @Override
   public List getCallerRoutines(CodePointer var1, boolean var2) {
      ArrayList var3 = new ArrayList();
      CallGraphVertex var4 = this.q(var1, false);
      if (var4 != null) {
         for (Long var6 : this.getCallers(var4, var2)) {
            for (Long var8 : this.RF.getContainedRoutineAddresses(var6)) {
               var3.add(this.RF.oW(var8));
            }
         }
      }

      return var3;
   }
}
