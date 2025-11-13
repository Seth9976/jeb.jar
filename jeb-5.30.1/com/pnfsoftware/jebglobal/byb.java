package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmuContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmuFrame;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;

public class byb implements IDEmuContext {
   private String q;
   private Deque RF = new ArrayDeque();
   private Deque xK = new ArrayDeque();

   byb(String var1) {
      this.q = var1;
   }

   @Override
   public IDEmuContext copy() {
      byb var1 = new byb(this.q);
      var1.RF = new ArrayDeque(this.RF.size());
      this.RF.forEach(var1x -> var1.RF.add(var1x.copy()));
      var1.xK = new ArrayDeque(this.xK);
      return var1;
   }

   @Override
   public String getName() {
      return this.q;
   }

   @Override
   public int getCountOfFrames() {
      return this.RF.size();
   }

   @Override
   public IDEmuFrame getFrame(int var1) {
      return (IDEmuFrame)new ArrayList(this.RF).get(var1);
   }

   public void q(IDEmuFrame var1) {
      this.RF.push(var1);
   }

   public IDEmuFrame q() {
      return (IDEmuFrame)this.RF.pop();
   }

   public IDEmuFrame RF() {
      return (IDEmuFrame)this.RF.peek();
   }

   @Override
   public boolean hasFrames() {
      return !this.RF.isEmpty();
   }

   @Override
   public Collection getFrames() {
      return Collections.unmodifiableCollection(this.RF);
   }

   @Override
   public Collection getOrigins() {
      return Collections.unmodifiableCollection(this.xK);
   }

   @Override
   public void pushOriginInfo(String var1) {
      this.xK.push(var1);
   }

   @Override
   public String toString() {
      return this.q;
   }
}
