package VANILLA_FSS_rev20110129.fss2008vanilla;

import java.util.Comparator;

/*
 * Copyright (c) 2011 Murilo Rebelo Pontes
 * murilo.pontes@gmail.com
 * 
 * GNU LESSER GENERAL PUBLIC LICENSE (Version 2.1, February 1999)
 */


public class FSS_ComparatorByFitnessGain implements Comparator<FSS_Fish>{

	@Override
	public int compare(FSS_Fish o1, FSS_Fish o2) {
		if (o1.delta_f<o2.delta_f) return -1;
		if (o1.delta_f>o2.delta_f) return 1;
		return 0;
	}

}
