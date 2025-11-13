package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Ser
public abstract class bkb implements Iterable {
   protected static final ILogger q = GlobalLog.getLogger(bkb.class);
   static boolean RF = true;
   @SerId(1)
   protected com.pnfsoftware.jeb.corei.parsers.dex.bK xK;
   @SerId(2)
   protected int Dw;
   @SerId(3)
   protected List Uv;
   @SerId(4)
   protected Map oW;
   @SerId(6)
   private String gO;

   public bkb(String var1, com.pnfsoftware.jeb.corei.parsers.dex.bK var2, int var3) {
      this.xK = var2;
      this.Dw = var3;
      this.Uv = new ArrayList(var3);
      this.oW = new HashMap(var3);
      this.gO = var1;
   }

   public int q() {
      return this.Uv.size();
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.bK RF() {
      return this.xK;
   }

   public int xK() {
      return this.Dw;
   }

   public synchronized bjx q(int var1) {
      return (bjx)this.Uv.get(var1);
   }

   public synchronized bjx xK(String var1) {
      return (bjx)this.oW.get(var1);
   }

   public synchronized List Dw() {
      return new ArrayList(this.Uv);
   }

   public List Uv() {
      return Collections.unmodifiableList(this.Uv);
   }

   public synchronized boolean q(String var1, bjx var2) {
      this.Uv.add(var2);
      if (!RF) {
         if (this.oW.put(var1, var2) != null) {
            throw new JebRuntimeException("Duplicate entry for key: " + var1);
         } else {
            return true;
         }
      } else if (this.oW.putIfAbsent(var1, var2) != null) {
         this.xK.logWarn(true, S.L("Duplicate key for dex pool entry: %s"), var1);
         return false;
      } else {
         return true;
      }
   }

   @Override
   public Iterator iterator() {
      return new bkb.eo();
   }

   @Override
   public synchronized String toString() {
      return Strings.ff("%s:%d", this.gO, this.Uv.size());
   }

   private class eo implements Iterator {
      int q;

      @Override
      public boolean hasNext() {
         return this.q < bkb.this.Uv.size();
      }

      public bjx q() {
         return (bjx)bkb.this.Uv.get(this.q++);
      }
   }
}
