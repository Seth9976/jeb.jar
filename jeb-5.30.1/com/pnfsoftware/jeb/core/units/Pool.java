package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Ser
public class Pool implements Iterable {
   @SerId(1)
   protected String poolname;
   @SerId(2)
   protected List list;

   public Pool(String var1) {
      this.poolname = var1;
      this.list = new ArrayList();
   }

   public Pool(String var1, int var2) {
      this.poolname = var1;
      this.list = new ArrayList(var2);
   }

   public String getName() {
      return this.poolname;
   }

   public int size() {
      return this.list.size();
   }

   public boolean isEmpty() {
      return this.list.isEmpty();
   }

   public void add(PoolEntry var1) {
      var1.setIndex(this.list.size());
      this.list.add(var1);
   }

   public PoolEntry get(int var1) {
      return (PoolEntry)this.list.get(var1);
   }

   public List getAll() {
      return this.list;
   }

   @Override
   public Iterator iterator() {
      return this.list.iterator();
   }

   @Override
   public String toString() {
      return Strings.ff("Pool_%s:%d", this.getName(), this.size());
   }
}
