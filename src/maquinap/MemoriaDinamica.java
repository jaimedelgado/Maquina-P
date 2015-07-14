package maquinap;

import java.util.ArrayList;
import java.util.Collections;

import maquinap.valores.Valor;

public class MemoriaDinamica {
	
	private static final int SIZE = 100000;
	
	private Valor[] memoria;
	private ArrayList<Integer> huecos;
	
	public MemoriaDinamica() {
		memoria = new Valor[SIZE];
		
		huecos = new ArrayList<Integer>();
		for(int i = 0; i < SIZE; i++){
			huecos.add(i);
		}
	}
	
	private void removeBlockRange(int start, int end){
		for(int i = start; i < end; i++){
			if(huecos.contains(i)){
				huecos.remove((Object)i);
			}
		}
	}
	
	private void addBlockRange(int start, int end){
		for(int i = start; i < end; i++){
			if(!huecos.contains(i)){
				huecos.add(i);
			}
		}
		
		ordenaHuecos();
	}
	
	public Valor getValor(int dir, int size){
		return null;
	}

	public Valor getValor(int dir){
		return memoria[dir];
	}
	
	public void setValor(int dir, Valor valor){
		memoria[dir] = valor;
		if(huecos.contains(dir)){
			huecos.remove(dir);
		}
	}
	
	public void setValor(int dir, Valor[] valor){
		for(Valor val : valor){
			setValor(dir, val);
			dir++;
		}
	}
	
	public void free(int dir, int size){
		addBlockRange(dir, dir+size);
	}
	
	public int alloc(int blocks){
		int i = 0;
		while(i < SIZE){
			for(int j = 0; j <= blocks; j++){
				if(j == blocks){
					removeBlockRange(i, i+j);
					return i;
				}
				if(!huecos.contains(i+j)){
					i = i+j;
					break;
				}
			}
			
			i++;
		}
		
		return -1;
	}
	
	private void ordenaHuecos(){
		Collections.sort(huecos);
	}
}
