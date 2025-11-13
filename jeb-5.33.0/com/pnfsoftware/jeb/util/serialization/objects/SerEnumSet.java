package com.pnfsoftware.jeb.util.serialization.objects;

import com.pnfsoftware.jeb.util.serialization.SerializerHelper;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerConstructor;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomWrite;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.io.IOException;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Ser
public class SerEnumSet implements Set {
   @SerTransient
   private EnumSet set;
   @SerId(1)
   private Class c;
   @SerId(2)
   private Set setForStorage;

   public static SerEnumSet wrap(Class var0, EnumSet var1) {
      if (var0 != null && var1 != null) {
         SerEnumSet var2 = new SerEnumSet();
         var2.c = var0;
         var2.set = var1;
         return var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @SerCustomWrite
   private void save(SerializerHelper var1) throws IOException {
      this.setForStorage = new HashSet(this.set.size());

      for (Enum var3 : this.set) {
         this.setForStorage.add(var3);
      }

      var1.saveStandard();
      this.setForStorage = null;
   }

   @SerCustomInitPostGraph
   private void postInit() {
      this.set = EnumSet.allOf(this.c);
      this.set.retainAll(this.setForStorage);
      this.setForStorage = null;
   }

   public EnumSet wrapped() {
      return this.set;
   }

   @Override
   public int size() {
      return this.set.size();
   }

   @Override
   public boolean isEmpty() {
      return this.set.isEmpty();
   }

   @Override
   public boolean contains(Object var1) {
      return this.set.contains(var1);
   }

   @Override
   public Iterator iterator() {
      return this.set.iterator();
   }

   @Override
   public Object[] toArray() {
      return this.set.toArray();
   }

   @Override
   public Object[] toArray(Object[] var1) {
      return this.set.toArray(var1);
   }

   public boolean add(Enum var1) {
      return this.set.add(var1);
   }

   @Override
   public boolean remove(Object var1) {
      return this.set.remove(var1);
   }

   @Override
   public boolean containsAll(Collection var1) {
      return this.set.containsAll(var1);
   }

   @Override
   public boolean addAll(Collection var1) {
      return this.set.addAll(var1);
   }

   @Override
   public boolean retainAll(Collection var1) {
      return this.set.retainAll(var1);
   }

   @Override
   public boolean removeAll(Collection var1) {
      return this.set.removeAll(var1);
   }

   @Override
   public void clear() {
      this.set.clear();
   }

   @Override
   public int hashCode() {
      return this.set.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      return this.set.equals(var1);
   }

   @Override
   public String toString() {
      return this.set.toString();
   }
}
