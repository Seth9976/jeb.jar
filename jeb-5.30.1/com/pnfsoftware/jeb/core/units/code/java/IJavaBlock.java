package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaBlock extends IJavaCompound, Iterable {
   int size();

   boolean isEmpty();

   IJavaStatement get(int var1);

   IJavaStatement getLast();

   void set(int var1, IJavaStatement var2);

   IJavaStatement remove(int var1);

   IJavaStatement removeLast();

   void removeMultiple(int var1, int var2);

   void removeRange(int var1, int var2);

   boolean remove(IJavaStatement var1);

   void add(IJavaStatement var1);

   void insert(int var1, IJavaStatement var2);

   void insertAll(int var1, IJavaBlock var2);

   void addAll(IJavaBlock var1);

   void insertMultiple(int var1, IJavaBlock var2, int var3, int var4, boolean var5);

   void addMultiple(IJavaBlock var1, int var2, int var3, boolean var4);

   void clear();

   void generateBody(JavaOutputSink var1);

   void generateBody(JavaOutputSink var1, boolean var2);

   void generateHeader(JavaOutputSink var1);

   void generateFooter(JavaOutputSink var1);

   IJavaBlock duplicate();
}
