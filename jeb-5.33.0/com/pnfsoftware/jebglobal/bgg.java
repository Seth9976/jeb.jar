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
public abstract class bgg implements Iterable {
   protected static final ILogger pC = GlobalLog.getLogger(bgg.class);
   static boolean A = true;
   @SerId(1)
   protected com.pnfsoftware.jeb.corei.parsers.dex.vi kS;
   @SerId(2)
   protected int wS;
   @SerId(3)
   protected List UT;
   @SerId(4)
   protected Map E;
   @SerId(6)
   private String sY;

   public bgg(String var1, com.pnfsoftware.jeb.corei.parsers.dex.vi var2, int var3) {
      this.kS = var2;
      this.wS = var3;
      this.UT = new ArrayList(var3);
      this.E = new HashMap(var3);
      this.sY = var1;
   }

   public int pC() {
      return this.UT.size();
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.vi A() {
      return this.kS;
   }

   public int kS() {
      return this.wS;
   }

   public synchronized bgc pC(int var1) {
      return (bgc)this.UT.get(var1);
   }

   public synchronized bgc kS(String var1) {
      return (bgc)this.E.get(var1);
   }

   public List wS() {
      return Collections.unmodifiableList(this.UT);
   }

   public synchronized boolean pC(String var1, bgc var2) {
      this.UT.add(var2);
      if (!A) {
         if (this.E.put(var1, var2) != null) {
            throw new JebRuntimeException("Duplicate entry for key: " + var1);
         } else {
            return true;
         }
      } else if (this.E.putIfAbsent(var1, var2) != null) {
         this.kS.logWarn(true, S.L("Duplicate key for dex pool entry: %s"), var1);
         return false;
      } else {
         return true;
      }
   }

   @Override
   public Iterator iterator() {
      return new bgg.Av();
   }

   @Override
   public synchronized String toString() {
      return Strings.ff("%s:%d", this.sY, this.UT.size());
   }

   private class Av implements Iterator {
      int pC;

      @Override
      public boolean hasNext() {
         return this.pC < bgg.this.UT.size();
      }

      public bgc pC() {
         return (bgc)bgg.this.UT.get(this.pC++);
      }
   }
}
