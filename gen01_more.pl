#!/usr/local/bin/perl
#
# composite series of images over a background image
#



$n = $ARGV[0];
$k = $ARGV[1];

#$zeros = join(",", split("", "0" x $n));
#print "$zeros\n";
for ($i=1; $i <= $n-1; $i++) {
	for ($j=0; $j <= $k; $j++) {
		my $bin = ("0" x $i).("*" x $j).("0" x ($n-$i));
		my @bits = split(//, $bin);
		$line = join(",", @bits);
		print $line."\n";
		$line =~ s/0/1/g;
		print $line."\n";
	}
}

