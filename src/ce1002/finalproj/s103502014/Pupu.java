package ce1002.finalproj.s103502014;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
public class Pupu extends JFrame {
	static int xdiv=1;
	static int ydiv=1;
	static int xdist=1;
	static int ydist=1;
	static int level=0;
	static JFrame[][] pupu=new JFrame[20][20];
	//Obsacles
	static int[][][] obst=
	{
		//level 0
		{
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		},
		//level 1
				{
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,0,0},
					{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
					{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
					{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
					{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
					{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
					{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
					{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
					{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
					{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
					{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
					{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
					{0,0,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				},
			//level 2
				{
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
					{0,0,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,0,0},
					{0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
					{0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
					{0,0,0,0,0,1,0,0,0,0,0,0,1,1,1,1,1,0,0,0},
					{0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
					{0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
					{0,0,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				},
			//level 3
				{
					{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
					{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
					{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
					{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
					{1,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1},
					{1,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1},
					{1,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1},
					{1,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1},
					{1,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1},
					{1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1},
					{1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1},
					{1,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1},
					{1,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1},
					{1,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1},
					{1,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1},
					{1,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1},
					{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
					{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
					{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
					{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				},
			//level 4
				//Sponsor: Ting-wei Lu
				//The laughing bun
				{
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
					{0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
					{0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
					{0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
					{0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
					{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
					{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
					{0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				},
			//level 5
				{
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
					{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
					{0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0},
					{0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0},
					{0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0},
					{0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0},
					{0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0},
					{0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0},
					{0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0},
					{0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0},
					{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
					{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				},
			//level 6
				{
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
					{0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
					{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
					{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
					{0,0,0,0,1,0,0,1,1,1,1,1,0,0,0,1,0,0,0,0},
					{0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0},
					{0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0},
					{0,0,0,0,1,0,0,1,1,1,1,1,0,0,0,1,0,0,0,0},
					{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
					{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
					{0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
					{0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				},
			//level 7
				{
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{1,1,1,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				},
			//level 8
				{
					{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
				},
			//level 9
				{
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
					{0,0,0,1,0,0,1,1,1,1,1,1,1,1,0,0,1,0,0,0},
					{0,0,0,1,0,0,1,1,1,1,1,1,1,1,0,0,1,0,0,0},
					{0,0,0,1,0,0,1,1,1,1,1,1,1,1,0,0,1,0,0,0},
					{0,0,0,1,0,0,1,1,1,1,1,1,1,1,0,0,1,0,0,0},
					{0,0,0,1,0,0,1,1,1,1,1,1,1,1,0,0,1,0,0,0},
					{0,0,0,1,0,0,1,1,1,1,1,1,1,1,0,0,1,0,0,0},
					{0,0,0,1,0,0,1,1,1,1,1,1,1,1,0,0,1,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
					{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
					{0,0,0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				},
	};
	static int[] startpositionx={10,10,10,4,0,7,0,5,0,0};
	static int[] startpositiony={10,10,10,3,0,7,5,7,7,2};
	public Pupu(int stage,int xdiv,int ydiv,int xdist,int ydist)//constructor
	{
		this.level=stage;
		this.xdiv=xdiv;
		this.ydiv=ydiv;
		this.xdist=xdist;
		this.ydist=ydist;
	}
	public void initcreate()
	{
		for(int i=0;i<xdiv;i++)
		{
			for(int j=0;j<ydiv;j++)
			{
				pupu[i][j]=new JFrame();
				pupu[i][j].getContentPane().setBackground(Color.yellow);
				pupu[i][j].setUndecorated(true);
				pupu[i][j].setLocation(i*xdist,j*ydist);
				pupu[i][j].setSize(xdist,ydist);
				pupu[i][j].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				pupu[i][j].setFocusableWindowState(false);
				if(obst[level][j][i]==1)
				{
					pupu[i][j].setVisible(true);
				}
			}
		}
	}
	public void change(int stage)//changing stage
	{
		this.level=stage;
		for(int i=0;i<xdiv;i++)
		{
			for(int j=0;j<ydiv;j++)
			{
				pupu[i][j].setVisible(false);
				if(obst[stage][j][i]==1)
				{
					pupu[i][j].setVisible(true);
				}
			}
		}
	}
	public int check(int x,int y)
	{
		if(obst[level][y][x]==1)return 1;
		else return 0;
	}
	public int getstartpositionx(int level)
	{
		return startpositionx[level];
	}
	public int getstartpositiony(int level)
	{
		return startpositiony[level];
	}
}