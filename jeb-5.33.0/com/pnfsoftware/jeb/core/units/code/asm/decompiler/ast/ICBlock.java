package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface ICBlock extends ICCompound, Iterable {
   void generateHeader(COutputSink var1);

   void generateBody(COutputSink var1, boolean var2);

   void generateFooter(COutputSink var1);

   void clear();

   int size();

   boolean isEmpty();

   List getAll();

   ICStatement get(int var1);

   ICStatement getLast();

   void set(int var1, ICStatement var2);

   void insert(int var1, ICStatement var2);

   void insertAll(int var1, ICBlock var2);

   ICBlock add(ICStatement var1);

   ICBlock addAll(ICStatement... var1);

   ICBlock addAll(ICBlock var1);

   ICStatement remove(int var1);

   ICStatement removeLast();

   boolean remove(ICStatement var1);

   ICBlock duplicate();
}
